package keep.internal

import keep.Cache
import keep.exceptions.CacheLoadException
import keep.load
import keep.loadOrNull
import keep.save
import kommander.expect
import kommander.expectFailure
import kommander.toBe
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlin.test.Test

abstract class AbstractCacheTest(val cache: Cache) {
    @Test
    fun should_be_able_to_load_and_save_primitively_easily() = runTest {
        val saved = cache.save("int", 1)
        val loaded: Int = cache.load("int")
        expect(saved).toBe(1)
        expect(loaded).toBe(1)
    }

    @Serializable
    data class Person(val name: String)

    @Test
    fun should_be_able_to_load_and_save_custom_classes_easily() = runTest {
        val saved = cache.save("john", Person("John"))
        val loaded = cache.load<Person>("john")

        expect(saved).toBe(Person("John"))
        expect(loaded).toBe(Person("John"))
    }

    @Test
    fun should_throw_cache_load_exception() = runTest {
        val err = expectFailure { cache.load<Int>("jane") }
        expect(err).toBe<CacheLoadException>()
        expect(err.message).toBe("Failed to load object with key=jane from the cache")
    }

    @Test
    fun should_throw_a_cache_load_exception_with_a_serialization_cause() = runTest {
        val err = expectFailure { cache.load<Any>("jane") }
        val exp = expect(err).toBe<CacheLoadException>()
        expect(exp.key).toBe("jane")
    }

    @Test
    fun should_return_Unit_when_an_existing_item_in_the_cache_was_removed() = runTest {
        val saved = cache.save("test", 1)
        val removed = cache.remove("test")
        expect(saved).toBe(1)
        expect(removed).toBe(Unit)
    }

    @Test
    fun should_return_null_when_removing_a_non_existent_key() = runTest {
        val result = cache.remove("pip")
        expect(result).toBe(null)
    }


    @Test
    fun should_clear_the_whole_cache() = runTest {
        cache.save("one", 1)
        cache.save("two", 2)
        cache.save("three", 3)
        cache.clear()

        expect(cache.loadOrNull<Int>("two")).toBeNull()

        expect(cache.keys().size).toBe(0)
    }
}