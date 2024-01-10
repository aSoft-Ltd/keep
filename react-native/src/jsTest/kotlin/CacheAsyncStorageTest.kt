import keep.CacheAsyncStorage
import keep.internal.AbstractCacheTest
import kommander.expect

import kotlin.test.Test

class CacheAsyncStorageTest : AbstractCacheTest(CacheAsyncStorage()) {

    @Test
    fun should_be_using_an_async_storage_cache() {
        expect(cache.toString()).toBe("CacheAsyncStorage(namespace=app)")
    }
}