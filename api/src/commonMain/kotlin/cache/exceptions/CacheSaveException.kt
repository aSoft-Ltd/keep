package cache.exceptions

@Deprecated("use keep instead")
class CacheSaveException(key: String, cause: Throwable) : CacheException(key, "Failed to save object with key `$key` to the cache", cause)