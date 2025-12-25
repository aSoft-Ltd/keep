package keep

import kotlin.jvm.JvmField

data class CacheMockConfig(
    override val namespace: String = CacheConfig.DEFAULT_NAMESPACE,
    val initialCache: MutableMap<String, Any?> = DEFAULT_MAP,
) : CacheConfig {
    companion object {
        @JvmField
        val DEFAULT_MAP = mutableMapOf<String, Any?>()
    }
}