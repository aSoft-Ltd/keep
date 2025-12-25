import keep.CacheMock
import keep.CacheMockConfig
import keep.internal.AbstractCacheTest
import kommander.IgnoreNative
import kommander.expect
import kotlin.test.Test

//@IgnoreOSX
@IgnoreNative
class CacheMockTest : AbstractCacheTest(CacheMock(config)) {

    companion object Companion {
        private val config = CacheMockConfig()
    }

    @Test
    fun should_be_using_a_mock_cache() {
        expect(cache.toString()).toBe("CacheMock(namespace=app)")
    }
}