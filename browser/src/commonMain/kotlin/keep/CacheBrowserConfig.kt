package keep

import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json

data class CacheBrowserConfig(
    override val namespace: String = CacheConfig.DEFAULT_NAMESPACE,
    val storage: Storage = localStorage,
    val codec: StringFormat = Json { encodeDefaults = true }
) : CacheConfig