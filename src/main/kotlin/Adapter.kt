@file:Suppress("OPT_IN_USAGE")

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json
import net.sergeych.boss_serialization.BossDecoder
import net.sergeych.boss_serialization_mp.BossEncoder
import net.sergeych.boss_serialization_mp.decodeBoss
import net.sergeych.mp_logger.LogTag
import net.sergeych.mp_logger.exception
import net.sergeych.mp_logger.warning
import net.sergeych.mptools.toDump
import kotlin.reflect.KType
import kotlin.reflect.typeOf

open class WithAdapter {
    internal var _adapter: Adapter<*>? = null

    val adapter: Adapter<*> get() = _adapter ?: throw IllegalStateException("adapter is not yet initialized")
}

open class CommandHost<T: WithAdapter> {
    private val handlers = mutableMapOf<String, suspend T.(ByteArray) -> ByteArray>()

    /**
     * Provide implementation for a specific command in type-safe compile-time checked manner. the command
     * should be declared with [command] invocation.
     */
    fun <A, R> on(ca: CommandDescriptor<A, R>, block: suspend T.(A) -> R) {
        handlers[ca.name] = {args ->
            val decodedArgs = BossDecoder.decodeFrom<A>(ca.ass, args)
            BossEncoder.encode(ca.rss, block(decodedArgs))
        }
    }

    fun handler(name: String) = handlers.get(name) ?: throw CommandNotFoundException(name)

    /**
     * Provide a command delegate that creates type-safe command descriptor containint command name and
     * types of it arguments and return value.
     */
    inline fun <reified A, reified R> command(name: String? = null): AdapterDelegate<T, A, R> {
        return AdapterDelegate(
            name,
            typeOf<A>(),
            typeOf<R>()
        )
    }
}


class CommandDescriptor<A, R>(
    val name: String,
    val ass: KType,
    val rss: KType,
) {
    suspend operator fun invoke(adapter: Adapter<*>, args: A): R =
        adapter.invokeCommand(this, args)

    @Suppress("UNCHECKED_CAST")
    suspend operator fun invoke(adapter: Adapter<*>): R = adapter.invokeCommand(this,Unit as A)

    operator fun <I: WithAdapter>invoke(commandHost: CommandHost<I>, block: suspend I.(A)->R) {
        commandHost.on(this, block)
    }
}



open class Adapter<T: WithAdapter>(
    private val instance: T,
    private val commandHost: CommandHost<T>,
    private val exceptionRegistry: ExceptionsRegistry = ExceptionsRegistry(),
    private val sendEncoded: suspend (data: ByteArray) -> Unit,
) {

    val scope = CoroutineScope(GlobalScope.coroutineContext)

    private val completions = mutableMapOf<Int, CompletableDeferred<ByteArray>>()
    private var lastId = 1
    private val access = Mutex()

    /**
     * Call the remote party for a type command. See [CommandHost] on how to declare and implement
     * such commands in parsec3. Suspends until receiving answer from a remote party.
     *
     * @param ca command descriptor, provided by [CommandHost.command], usually, it should be a val in the
     *            [CommandHost] or derived instance.
     * @param args command specific args of any serializable type
     * @return value from remote partm any serializable type.
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun <A, R> invokeCommand(ca: CommandDescriptor<A, R>, args: A = Unit as A): R {
        var myId = -1
        return CompletableDeferred<ByteArray>().also { dr ->
            sendPackage(
                access.withLock {
//                    debug { "calling $lastId:${ca.name}($args)" }
                    completions[lastId] = dr
                    myId = lastId
                    Package.Command(lastId++, ca.name, BossEncoder.encode(ca.ass, args))
                }
            )
        }.await().let {
//            debug { "result  $myId:$it" }
            BossDecoder .decodeFrom(ca.rss, it)
        }
    }

    /**
     * Cancels the scope that is used to call incoming commands. Cancelling the scope effectively cancels any
     * unfinished commands. It _will not wait for its completion_.
     *
     * Not calling it might cause unknown number of pending command processing coroutines to remain active.
     */
    fun cancel() {
        scope.cancel()
    }

    /**
     * merge exceptions registry with current (existing entries will be overwritten)
     */
    @Suppress("unused")
    fun registerErrors(otherRegistry: ExceptionsRegistry) {
        exceptionRegistry.putAll(otherRegistry)
    }

    private suspend fun processIncomingPackage(pe: Package) {
        when (pe) {
            is Package.Command -> {
                    scope.launch {
                        try {
                            val handler = commandHost.handler(pe.name)
                            val result = handler.invoke(instance, pe.args)
                            sendPackage(
                                Package.Response(pe.id, result)
                            )
                        } catch (ae: ParsecException) {
                            sendPackage(Package.Response(pe.id, null, ae.code, ae.text))
                        } catch (ex: Throwable) {
                            exceptionRegistry.classCodes[ex::class]?.let { code ->
                                sendPackage(Package.Response(pe.id, null, code, ex.toString()))
                            } ?: run {
                                ex.printStackTrace()
                                sendPackage(Package.Response(pe.id, null, "UNKNOWN_ERROR", ex.toString()))
                            }
                        }
                    }
            }

            is Package.Response -> {
                val dr = access.withLock { completions.remove(pe.toId) }
                if (dr == null)
                    println("response to unregistered toId: ${pe.toId}, ignoring")
                else {
                    if (pe.result != null)
                        dr.complete(pe.result)
                    else
                        dr.completeExceptionally(
                            pe.errorCode?.let { exceptionRegistry.getException(it, pe.errorText) }
                                ?: InvalidFrameException("invalid package: no result, no error code")
                        )
                }
            }
        }
    }

    private suspend fun sendPackage(pe: Package) {
        sendEncoded(BossEncoder.encode(pe))
    }

    /**
     * Provide an incoming frame to the adapter. Consumer software receives binary blocks from whatever
     * protocol it uses (say, UDP) and feed them to this method.
     */
    suspend fun receiveFrame(data: ByteArray) {
        try {
            processIncomingPackage(data.decodeBoss())
        } catch (x: Exception) {
            throw RuntimeException("unexpected error processing frame: \n${data.toDump()}", x)
        }
    }

    companion object {
        val format = Json { prettyPrint = true }
    }
}

