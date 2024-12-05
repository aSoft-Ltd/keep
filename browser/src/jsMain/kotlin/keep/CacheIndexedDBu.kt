package keep

import keep.exceptions.CacheLoadException
import keep.exceptions.CacheMissException
import keep.idb.*
import koncurrent.Later
import koncurrent.later
import kotlinx.serialization.KSerializer
import kotlin.js.Promise

class CacheIndexedDBu(val config: CacheIndexedDBConfigu = CacheIndexedDBConfigu()) : Cache {
    private val executor = config.executor
    private val json = config.codec
    private val namespace = config.namespace
    private val dbName = "${config.dbName}-${namespace}"

    private fun getDatabase(): Promise<IDBDatabase> = Promise { resolve, reject ->
        val request = indexedDB.open(dbName, config.version)
        
        request.onupgradeneeded = { event ->
            val db = (event.target as IDBOpenDBRequest).result
            db.createObjectStore(config.storeName)
        }
        
        request.onsuccess = {
            resolve(request.result)
        }
        
        request.onerror = {
            reject(Throwable("Failed to open IndexedDB"))
        }
    }

    private fun <T> withStore(
        mode: String = "readonly",
        block: (IDBObjectStore) -> IDBRequest
    ): Promise<T> = Promise { resolve, reject ->
        getDatabase().then { db ->
            val transaction = db.transaction(arrayOf(config.storeName), mode)
            val store = transaction.objectStore(config.storeName)
            val request = block(store)

            request.onsuccess = {
                @Suppress("UNCHECKED_CAST")
                resolve(request.result as T)
                db.close()
            }

            request.onerror = {
                reject(Throwable("IndexedDB operation failed"))
                db.close()
            }
        }.catch { reject(it) }
    }

    override fun size() = executor.later {
        TODO()
//        withStore { store ->
//            store.getAllKeys()
//        }.await().unsafeCast<Array<String>>().size
    }

    override fun keys() = executor.later {
        TODO()
//        withStore { store ->
//            store.getAllKeys()
//        }.await().unsafeCast<Array<String>>().toSet()
    }

    override fun clear() = executor.later {
        TODO()
//        withStore("readwrite") { store ->
//            store.clear()
//        }.await()
    }

    override fun remove(key: String) = executor.later {
        TODO()
//        withStore("readwrite") { store ->
//            store.delete("$namespace:$key")
//        }.await()
//        Unit
    }

    override fun namespaced(namespace: String) = CacheIndexedDBu(
        config.copy(namespace = "${config.namespace}.$namespace")
    )

    override fun <T> save(key: String, obj: T, serializer: KSerializer<T>): Later<T> = executor.later {
        val serialized = json.encodeToString(serializer, obj)
        TODO()
//        withStore("readwrite") { store ->
//            store.put(serialized, "$namespace:$key")
//        }.await()
//        obj
    }

    override fun <T> load(key: String, serializer: KSerializer<T>): Later<T> = Later(executor) { resolve, reject ->
        TODO()
//        try {
//            val result = withStore { store ->
//                store.get("$namespace:$key")
//            }.await()
//
//            if (result == null) {
//                reject(CacheMissException(key))
//                return@Later
//            }
//
//            try {
//                val decoded = json.decodeFromString(serializer, result.toString())
//                resolve(decoded)
//            } catch (err: Throwable) {
//                reject(CacheLoadException(key, cause = err))
//            }
//        } catch (err: Throwable) {
//            reject(CacheLoadException(key, cause = err))
//        }
    }

    override fun toString(): String = "CacheIndexedDB(namespace=$namespace)"
} 