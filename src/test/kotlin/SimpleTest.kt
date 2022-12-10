import kotlin.test.Test
import kotlin.test.assertEquals

// The class below causes inner compiler error "Validation failed in file StreamingJsonDecoder.kt"
// Comment this class out to avoid it:
@Suppress("unused")
class MyApi<T: WithAdapter>: CommandHost<T>() {
    val foo by command<String,String>()
}

class TestClient {
    @Test
    fun testGreet() {
        assertEquals("world", greet())
    }
} 