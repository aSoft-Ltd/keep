import keep.CacheFile
import keep.CacheFileConfig
import keep.internal.AbstractCacheTest
import kommander.expect
import keep.CacheMock
import keep.CacheMockConfig
import koncurrent.Executors
import okio.Path
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem

import kotlin.test.Test

class CacheFakeFileSystemTest : AbstractCacheTest(CacheFile(config)) {

    companion object {
        private val config = CacheFileConfig(
            fs = FakeFileSystem(),
            path = "/cache".toPath()
        )
    }

    @Test
    fun should_be_using_a_cache_file_object() {
        expect(cache.toString()).toBe("CacheFile(namespace=app)")
    }
}