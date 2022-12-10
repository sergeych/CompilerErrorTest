import kotlin.reflect.KClass

open class ParsecException(val code: String, val text: String?=null, reason: Throwable?=null) : Exception(
    text?.let { "($code): $text" } ?: code, reason
)

class CommandNotFoundException(name: String): ParsecException(ExceptionsRegistry.commandNotFoundCode,name)

class InvalidFrameException(text: String): ParsecException(ExceptionsRegistry.invalidFrameCode, text)

class UnknownCodeException(code: String,message: String?): ParsecException(code, message)

class UnknownException(message: String?): ParsecException(ExceptionsRegistry.unknownErrorCode, message)

/**
 * Registry to restore exceptions from parsec block data and encode them to.
 * Serializing exceptions is dangerous: being a OS-bound
 * objects, exceptions can carry too much sensitive or useless information (e.g. call stack), and serializng
 * actual exceptions could be a pain, so parsec3 serializes exception information as 2 parameters: a code string
 * which is very much like old good (and awful in a way) `C ERRNO`, and an optional message.
 *
 * This class reconstructs exceptions from these parameters using a registry, that is pre-filled with application
 * codes and actual exception classes. Then [Adapter<T>] uses it to restore and throw actual exception on the calling
 * party side.
 *
 * The good idea is to share one object inheriting from refistry to hold all exceptions info in one place
 * and share it betweem client and server code.
 *
 * Note that exception registry is used to properly serialize registered exceptions, so as soon as you have
 * registered some code and exception, you can throw it inside command implementation and it will be
 * properly resotred on the remote side.
 */
open class ExceptionsRegistry {

    val handlers = mutableMapOf<String, (String?) -> Throwable>().also {
        // predefined exceptions:
        it[commandNotFoundCode] = { CommandNotFoundException(it ?: "???") }
        it[unknownErrorCode] = { UnknownException(it ?: "???") }
    }

    val classCodes = mutableMapOf<KClass<*>, String>()

    /**
     * Register an exception with a code with a handler that creates its instance. Note that the
     * handler _should not throw anything_ but rather create an instance of the exception.
     */
    inline fun <reified T : Throwable> register(code: String?=null, noinline block: (message: String?) -> T) {
        val _code = code ?: T::class.simpleName!!
        handlers[_code] = block
        classCodes[T::class] = _code
    }

    /**
     * Put all registere exception from another registry overriding existing ones if any.
     */
    fun putAll(other: ExceptionsRegistry) {
        classCodes.putAll(other.classCodes)
        handlers.putAll(other.handlers)
    }

    /**
     * raise the exception using the proper handler. Throws [UnknownCodeException] of there is no handler
     * for a given code.
     */
    @Suppress("unused")
    internal fun raise(code: String, message: String?): Nothing {
        throw getException(code, message)
    }

    /**
     * create the exception instanceusing the proper handler, or an [UnknownCodeException] if handler not found
     */
    internal fun getException(code: String, message: String?): Throwable =
        handlers[code]?.let { it(message) } ?: UnknownCodeException(code, message)

    init {
        register("illegal state") { IllegalStateException(it) }
        register("illegal argument") { IllegalArgumentException(it) }
    }

    companion object {
        val commandNotFoundCode = "_COMMAND_NOT_FOUND"
        val unknownErrorCode = "_UNKNOWN_ERROR"
        val invalidFrameCode = "_FRAME_INVALID"
    }

}