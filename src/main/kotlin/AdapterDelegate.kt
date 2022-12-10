import kotlin.reflect.KProperty
import kotlin.reflect.KType

/**
 * Delegate to generate proper [CommandDescriptor] instances. See [CommandHost] for usage sample, and
 * [Adapter] form more information/
 */
class AdapterDelegate<I, A, R>(
    val overrideName: String? = null,
    val ass: KType,
    val rss: KType,
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): CommandDescriptor<A, R> {
        return CommandDescriptor(
            overrideName ?: property.name,
            ass, rss
        )
    }
}