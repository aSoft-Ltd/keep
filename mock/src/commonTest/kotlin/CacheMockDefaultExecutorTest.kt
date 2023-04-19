import keep.internal.AbstractCacheTest
import kommander.expect
import keep.CacheMock
import keep.CacheMockConfig
import koncurrent.Executors

import kotlin.test.Test

class CacheMockDefaultExecutorTest : AbstractCacheTest(CacheMock(config)) {

    companion object {
        private val config = CacheMockConfig(
            executor = Executors.default()
        )
    }

    @Test
    fun should_be_using_a_mock_cache() {
        expect(cache.toString()).toBe("CacheMock(namespace=app)")
    }
}