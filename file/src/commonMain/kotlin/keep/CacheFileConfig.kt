package keep

import koncurrent.Executor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import okio.FileSystem
import okio.Path

data class CacheFileConfig(
    val fs: FileSystem,
    val dir: Path,
    override val namespace: String = CacheConfig.DEFAULT_NAMESPACE,
    override val executor: Executor = CacheConfig.DEFAULT_EXECUTOR,
    val codec: StringFormat = Json { encodeDefaults = true },
    val extension: String = "cache"
) : CacheConfig