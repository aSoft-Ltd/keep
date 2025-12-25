@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package keep

import keep.exceptions.CacheLoadException
import keep.exceptions.CacheSaveException
import kotlinx.JsExport
import kotlinx.serialization.KSerializer

/**
 * An interface to be able to [Cache] different objects
 */
interface Cache {
    /**
     * Should return the set of all available keys in the [Cache]
     */
    suspend fun keys(): Set<String>

    /**
     * Should return the size of the [Cache] which should ideally equal the number of [keys]
     */
    suspend fun size(): Int

    /**
     * Clears the entire [Cache]
     */
    suspend fun clear(): Unit

    /**
     * Removes a [key] from the [Cache]
     * @return the removed object or null if nothing was removed
     */
    suspend fun remove(key: String): Unit?

    /**
     * Create a [Cache] that is further namespaced with [namespace]
     * @param namespace the namespace to further namespace the [Cache] with
     * @return [Cache]
     */
    fun namespaced(namespace: String): Cache

    /**
     * Save object [T] on to the [Cache] with a [key] and its serializer [serializer]
     *
     * @return a [Later] that
     * - on success: resolves the saved object as it was cached
     * - on failure: rejects with a [CacheSaveException]
     */
    suspend fun <T> save(key: String, obj: T, serializer: KSerializer<T>): T

    /**
     * Load object [T] from the [Cache], that was saved with a [key] and its serializer [serializer]
     *
     * @return a [Later] that
     * - on success: resolves to the cached object
     * - on failure: rejects with a [CacheLoadException]
     */
    suspend fun <T> load(key: String, serializer: KSerializer<T>): T
}