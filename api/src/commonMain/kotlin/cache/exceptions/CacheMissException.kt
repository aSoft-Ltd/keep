package cache.exceptions

@Deprecated("use keep instead")
open class CacheMissException(key: String, cause: Throwable? = null) : CacheLoadException(key, cause = cause)