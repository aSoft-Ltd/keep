package keep.exceptions

open class CacheMissException(key: String, cause: Throwable? = null) : CacheLoadException(key, cause = cause)