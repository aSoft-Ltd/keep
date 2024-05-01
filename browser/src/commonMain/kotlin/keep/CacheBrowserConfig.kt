package keep

import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json

data class CacheBrowserConfig(
    override val namespace: String = CacheConfig.DEFAULT_NAMESPACE,
    override val executor: Executor = CacheConfig.DEFAULT_EXECUTOR,
    val storage: Storage = localStorage,
    val codec: StringFormat = Json { encodeDefaults = true }
) : CacheConfig