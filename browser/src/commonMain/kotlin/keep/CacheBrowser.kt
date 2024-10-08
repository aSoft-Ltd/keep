package keep

import keep.exceptions.CacheLoadException
import keep.exceptions.CacheMissException
import koncurrent.Later
import koncurrent.later
import kotlinx.serialization.KSerializer

class CacheBrowser(val config: CacheBrowserConfig = CacheBrowserConfig()) : Cache {
    private val storage = config.storage

    private val executor = config.executor

    private val json = config.codec

    private val namespace = config.namespace

    override fun size() = executor.later { storage.length }

    override fun keys() = executor.later {
        buildSet {
            for (i in 0 until storage.length) add(storage.key(i) as String)
        }
    }

    override fun namespaced(namespace: String) = CacheBrowser(config.copy(namespace = "${config.namespace}.${namespace}"))

    override fun <T> save(key: String, obj: T, serializer: KSerializer<T>) = executor.later {
        storage.setItem("${namespace}:${key}", json.encodeToString(serializer, obj))
        obj
    }

    override fun <T> load(key: String, serializer: KSerializer<T>): Later<T> = Later(executor) { res, rej ->
        val js = storage.getItem("${namespace}:${key}")
        if (js != null) try {
            res(json.decodeFromString(serializer, js))
        } catch (err: Throwable) {
            rej(CacheLoadException(key, cause = err))
        } else rej(CacheMissException(key))
    }

    override fun remove(key: String): Later<Unit?> = executor.later {
        val item = storage.getItem("${namespace}:${key}")
        storage.removeItem("${namespace}:${key}")
        if (item != null) Unit else null
    }

    override fun clear(): Later<Unit> = Later(storage.clear())

    override fun toString(): String = "CacheBrowser(namespace=$namespace)"
}