import keep.CacheAsyncStorage
import keep.internal.AbstractCacheTest
import kommander.expect
import keep.CacheMock

import kotlin.test.Test

class CacheAsyncStorageTest : AbstractCacheTest(CacheAsyncStorage()) {

    companion object {

    }

    @Test
    fun should_be_using_an_async_storage_cache() {
        expect(cache.toString()).toBe("CacheAsyncStorage(namespace=app)")
    }
}