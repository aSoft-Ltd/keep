package keep

import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json

data class CacheIndexedDBTooling(
    override val namespace: String = CacheConfig.DEFAULT_NAMESPACE,
    override val executor: Executor = CacheConfig.DEFAULT_EXECUTOR,
    val dbName: String = "keep-cache",
    val storeName: String = "cache-store",
    val version: Int = 1,
    val codec: StringFormat = Json { encodeDefaults = true }
) : CacheConfig 