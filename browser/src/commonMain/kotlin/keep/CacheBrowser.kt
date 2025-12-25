package keep

import keep.exceptions.CacheLoadException
import keep.exceptions.CacheMissException
import kotlinx.serialization.KSerializer

class CacheBrowser(val config: CacheBrowserConfig = CacheBrowserConfig()) : Cache {
    private val storage = config.storage

    private val json = config.codec

    private val namespace = config.namespace

    override suspend fun size() = storage.length

    override suspend fun keys() = buildSet {
        for (i in 0 until storage.length) add(storage.key(i) as String)
    }

    override fun namespaced(namespace: String) = CacheBrowser(config.copy(namespace = "${config.namespace}.${namespace}"))

    override suspend fun <T> save(key: String, obj: T, serializer: KSerializer<T>): T {
        storage.setItem("${namespace}:${key}", json.encodeToString(serializer, obj))
        return obj
    }

    override suspend fun <T> load(key: String, serializer: KSerializer<T>): T {
        val js = storage.getItem("${namespace}:${key}")
        if (js != null) try {
            return json.decodeFromString(serializer, js)
        } catch (err: Throwable) {
            throw CacheLoadException(key, cause = err)
        } else throw (CacheMissException(key))
    }

    override suspend fun remove(key: String): Unit? {
        val item = storage.getItem("${namespace}:${key}")
        storage.removeItem("${namespace}:${key}")
        return if (item != null) Unit else null
    }

    override suspend fun clear() = storage.clear()

    override fun toString(): String = "CacheBrowser(namespace=$namespace)"
}