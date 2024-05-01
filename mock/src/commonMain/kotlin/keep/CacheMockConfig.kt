package keep

import koncurrent.Executor
import koncurrent.MockExecutor
import kotlin.jvm.JvmField

data class CacheMockConfig(
    override val namespace: String = CacheConfig.DEFAULT_NAMESPACE,
    val initialCache: MutableMap<String, Any?> = DEFAULT_MAP,
    override val executor: Executor = DEFAULT_EXECUTOR
) : CacheConfig {
    companion object {
        @JvmField
        val DEFAULT_MAP = mutableMapOf<String, Any?>()

        @JvmField
        val DEFAULT_EXECUTOR = MockExecutor(name = "Mock Cache Executor")
    }
}