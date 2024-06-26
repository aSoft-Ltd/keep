package keep

import keep.exceptions.CacheMissException
import koncurrent.Later
import koncurrent.later
import kotlinx.serialization.KSerializer

class CacheMock(val config: CacheMockConfig = CacheMockConfig()) : Cache {
    private val cache = config.initialCache

    private val executor get() = config.executor

    private val namespace get() = config.namespace

    override fun keys(): Later<Set<String>> = executor.later { cache.keys }

    override fun size(): Later<Int> = executor.later { cache.size }

    override fun <T> save(key: String, obj: T, serializer: KSerializer<T>) = Later(executor) { resolve, _ ->
        cache["$namespace:$key"] = obj
        resolve(obj)
    }

    override fun namespaced(namespace: String) = CacheMock(config.copy(namespace = "${config.namespace}.$namespace"))

    override fun <T> load(key: String, serializer: KSerializer<T>) = Later(executor) { resolve, reject ->
        val obj = cache["$namespace:$key"]
        if (obj != null) resolve(obj as T) else reject(CacheMissException(key))
    }

    override fun remove(key: String) = executor.later {
        val removed = cache.remove("$namespace:$key")
        if (removed != null) Unit else null
    }

    override fun clear() = executor.later { cache.clear() }

    override fun toString(): String = "CacheMock(namespace=$namespace)"
}