package keep

import keep.exceptions.CacheMissException
import kotlinx.serialization.KSerializer

class CacheMock(val config: CacheMockConfig = CacheMockConfig()) : Cache {
    private val cache = config.initialCache

    private val namespace get() = config.namespace

    override suspend fun keys(): Set<String> = cache.keys

    override suspend fun size(): Int = cache.size

    override suspend fun <T> save(key: String, obj: T, serializer: KSerializer<T>): T {
        cache["$namespace:$key"] = obj
        return obj
    }

    override fun namespaced(namespace: String) = CacheMock(config.copy(namespace = "${config.namespace}.$namespace"))

    override suspend fun <T> load(key: String, serializer: KSerializer<T>): T = cache["$namespace:$key"] as? T ?: throw CacheMissException(key)

    override suspend fun remove(key: String): Unit? {
        val removed = cache.remove("$namespace:$key")
        return if (removed != null) Unit else null
    }

    override suspend fun clear() = cache.clear()

    override fun toString(): String = "CacheMock(namespace=$namespace)"
}