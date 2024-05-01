package keep

import keep.npm.AsyncStorage
import keep.npm.ReactNativeAsyncStorage
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json

data class CacheAsyncStorageConfig(
    override val namespace: String = CacheConfig.DEFAULT_NAMESPACE,
    val storage: ReactNativeAsyncStorage = AsyncStorage,
    val codec: StringFormat = Json { encodeDefaults = true },
    override val executor: Executor = CacheConfig.DEFAULT_EXECUTOR
) : CacheConfig