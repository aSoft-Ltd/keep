@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package keep

import keep.exceptions.CacheLoadException
import keep.exceptions.CacheSaveException
import kotlinx.serialization.KSerializer
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import kotlinx.JsExport

/**
 * An interface to be able to [Cache] different objects
 */
interface Cache {
    /**
     * Should return the set of all available keys in the [Cache]
     */
    fun keys(): Later<Set<String>>

    /**
     * Should return the size of the [Cache] which should ideally equal the number of [keys]
     */
    fun size(): Later<Int>

    /**
     * Clears the entire [Cache]
     */
    fun clear(): Later<Unit>

    /**
     * Removes a [key] from the [Cache]
     * @return the removed object or null if nothing was removed
     */
    fun remove(key: String): Later<Unit?>

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
    fun <T> save(key: String, obj: T, serializer: KSerializer<T>): Later<T>

    /**
     * Load object [T] from the [Cache], that was saved with a [key] and its serializer [serializer]
     *
     * @return a [Later] that
     * - on success: resolves to the cached object
     * - on failure: rejects with a [CacheLoadException]
     */
    fun <T> load(key: String, serializer: KSerializer<T>): Later<T>
}