package cache.exceptions

@Deprecated("use keep instead")
sealed class CacheException(
    open val key: String,
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
