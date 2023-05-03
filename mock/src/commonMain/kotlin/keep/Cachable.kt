package keep

fun Cacheable(cache: Cache = CacheMock()): Cacheable = object : Cacheable {
    override val cache: Cache = cache
}