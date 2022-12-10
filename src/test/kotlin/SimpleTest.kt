import kotlin.test.Test
import kotlin.test.assertEquals

class MyApi<T: WithAdapter>: CommandHost<T>() {
    val foo by command<String,String>()
}

class TestClient {
    @Test
    fun testGreet() {
        assertEquals("world", greet())
    }
} 