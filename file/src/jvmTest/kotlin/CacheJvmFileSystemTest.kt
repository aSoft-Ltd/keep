import keep.CacheFile
import keep.CacheFileConfig
import keep.internal.AbstractCacheTest
import keep.save
import kommander.expect
import koncurrent.later.await
import koncurrent.later.andThen
import kotlinx.coroutines.test.runTest
import okio.FileSystem
import okio.Path.Companion.toPath

import kotlin.test.Test

class CacheJvmFileSystemTest : AbstractCacheTest(CacheFile(config)) {

    companion object {
        private val config = CacheFileConfig(
            fs = FileSystem.SYSTEM,
            dir = "/tmp/foundation/cache".toPath(),
            namespace = "test"
        )
    }

    @Test
    fun should_be_using_a_cache_file_object() {
        expect(cache.toString()).toBe("CacheFile(namespace=test)")
    }

    @Test
    fun should_save_a_bunch_of_things_in_the_tmp_dir() = runTest {
        cache.save("author", mapOf("name" to "anderson")).andThen {
            cache.save("hobbies", listOf("Programming", "Gaming", "Tech"))
        }.await()
    }
}