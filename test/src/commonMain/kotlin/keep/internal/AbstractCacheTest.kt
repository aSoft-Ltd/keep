package keep.internal

import keep.Cache
import keep.exceptions.CacheLoadException
import keep.load
import keep.loadOrNull
import keep.save
import kommander.expect
import kommander.expectFailure
import kommander.expectFailureWith
import kommander.expectFunction
import kommander.toBe
import koncurrent.awaited.andThen
import koncurrent.awaited.catch
import koncurrent.later.await
import koncurrent.awaited.then
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlin.test.Test

abstract class AbstractCacheTest(val cache: Cache) {
    @Test
    fun should_be_able_to_load_and_save_primitively_easily() = runTest {
        val result = cache.save("int", 1).andThen { cache.load<Int>("int") }.await()
        expect(result).toBe(1)
    }

    @Serializable
    data class Person(val name: String)

    @Test
    fun should_be_able_to_load_and_save_custom_classes_easily() = runTest {
        val result = cache.save("john", Person("John")).andThen { cache.load<Person>("john") }.await()

        expect(result).toBe(Person("John"))
    }

    @Test
    fun should_throw_cache_load_exception() = runTest {
        val err = expectFailure { cache.load<Int>("jane").await() }
        expect(err).toBe<CacheLoadException>()
        expect(err.message).toBe("Failed to load object with key=jane from the cache")
    }

    @Test
    fun should_throw_a_cache_load_exception_with_a_serialization_cause() = runTest {
        val err = expectFailure { cache.load<Any>("jane").await() }
        val exp = expect(err).toBe<CacheLoadException>()
        expect(exp.key).toBe("jane")
    }

    @Test
    fun should_return_Unit_when_an_existing_item_in_the_cache_was_removed() = runTest {
        val result = cache.save("test", 1).andThen { cache.remove("test") }.await()
        expect(result).toBe(Unit)
    }

    @Test
    fun should_return_null_when_removing_a_non_existent_key() = runTest {
        val result = cache.remove("pip").await()
        expect(result).toBe(null)
    }


    @Test
    fun should_clear_the_whole_cache() = runTest {
        cache.save("one", 1).andThen {
            println("one")
            cache.save<Int>("two", 2)
        }.andThen {
            println("two")
            cache.save<Int>("three", 3)
        }.andThen {
            println("three")
            cache.clear()
        }.await()

        expect(cache.loadOrNull<Int>("two").await()).toBeNull()

        expect(cache.keys().await().size).toBe(0)
    }
}