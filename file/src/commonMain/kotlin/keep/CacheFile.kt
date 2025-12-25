package keep

import keep.exceptions.CacheLoadException
import keep.exceptions.CacheSaveException
import kotlinx.serialization.KSerializer

class CacheFile(val config: CacheFileConfig) : Cache {
    private val namespace get() = config.namespace
    private val fs get() = config.fs
    private val ext get() = config.extension
    private val codec get() = config.codec

    private val root by lazy {
        if (!fs.exists(config.dir / namespace)) fs.createDirectories(config.dir / namespace)
        config.dir / namespace
    }

    override suspend fun keys(): Set<String> = fs.list(root).map { it.name.replace(".$ext", "") }.toSet()

    override fun namespaced(namespace: String) = CacheFile(config.copy(namespace = "${config.namespace}.$namespace"))

    override suspend fun size() = keys().size

    override suspend fun clear() {
        fs.deleteRecursively(root, mustExist = false)
        fs.createDirectories(root)
    }

    override suspend fun remove(key: String): Unit? {
        val filename = root / "$key.$ext"
        return if (fs.exists(filename)) try {
            fs.delete(filename, mustExist = false)
            Unit
        } catch (err: Throwable) {
            null
        } else null
    }

    override suspend fun <T> save(key: String, obj: T, serializer: KSerializer<T>): T {
        try {
            val filename = root / "$key.$ext"
            fs.write(filename) { writeUtf8(codec.encodeToString(serializer, obj)) }
            return obj
        } catch (err: Throwable) {
            throw CacheSaveException(key, cause = err)
        }
    }

    override suspend fun <T> load(key: String, serializer: KSerializer<T>): T {
        try {
            val filename = root / "$key.$ext"
            val content = fs.read(filename) { readUtf8() }
            return codec.decodeFromString(serializer, content)
        } catch (err: Throwable) {
            throw CacheLoadException(key, cause = err)
        }
    }

    override fun toString() = "CacheFile(namespace=$namespace)"
}