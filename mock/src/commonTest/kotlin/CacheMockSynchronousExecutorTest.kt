import keep.internal.AbstractCacheTest
import kommander.expect
import keep.CacheMock
import keep.CacheMockConfig
import kommander.IgnoreNative
import kommander.IgnoreOSX
import koncurrent.Executors
import koncurrent.MockExecutor
import kotlin.test.Test

//@IgnoreOSX
@IgnoreNative
class CacheMockSynchronousExecutorTest : AbstractCacheTest(CacheMock(config)) {

    companion object {
        private val config = CacheMockConfig(
            executor = MockExecutor()
        )
    }

    @Test
    fun should_be_using_a_mock_cache() {
        expect(cache.toString()).toBe("CacheMock(namespace=app)")
    }
}