import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The parsec3 package transmit requests and responses over the parsec3 channel.
 */
@Serializable
@SerialName("p3")
sealed class Package {
    /**
     * Invoke a remote command.
     * @param id command is a monotonously growing number, that could reset to 0 after getting close
     *           to `Int.MAX_VALUE`, for example. It uniquely identifies a command that is waiting for an answer.
     * @param name command's name
     * @param args whatever arguments the command accepts serialized with BOSS.
     */
    @Serializable
    @SerialName("cmd")
    data class Command(val id: Int, val name: String, val args: ByteArray) : Package()


    /**
     * Response to a previously issued command. See [ExceptionsRegistry] and [ParsecException] for more information
     * on passing errors.
     *
     * @param tiId if of the command this response is for
     * @param result packed result. If null, it means the command has thrown an exception and [errorCode] must
     *               not be null
     * @param errorCode exception code, if not null then result must be ignored (and assumed to be null).
     */
    @Serializable
    @SerialName("rsp")
    data class Response(
        val toId: Int,
        val result: ByteArray? = null,
        val errorCode: String? = null,
        val errorText: String? = null,
    ) : Package() {
        init {
            if (result == null && errorCode == null)
                throw IllegalArgumentException("either result or errorCode must not be null")
        }
    }

//    @Serializable
//    data class Push(val payload: PushPayload) : Package()
}


