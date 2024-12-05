package keep.idb

import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget

external class IDBDatabase : EventTarget {
    val name: String
    val version: Int
    fun createObjectStore(name: String, options: dynamic = definedExternally): IDBObjectStore
    fun transaction(storeNames: Array<String>, mode: String = definedExternally): IDBTransaction
    fun close()
}

external class IDBOpenDBRequest : EventTarget {
    var onupgradeneeded: ((Event) -> Any)?
    var onsuccess: ((Event) -> Any)?
    var onerror: ((Event) -> Any)?
    val result: IDBDatabase
}

external class IDBTransaction : EventTarget {
    fun objectStore(name: String): IDBObjectStore
    var oncomplete: ((Event) -> Any)?
    var onerror: ((Event) -> Any)?
}

external interface IDBObjectStore {
    fun put(value: Any, key: Any = definedExternally): IDBRequest
    fun get(key: Any): IDBRequest
    fun delete(key: Any): IDBRequest
    fun clear(): IDBRequest
    fun getAllKeys(): IDBRequest
}

external class IDBRequest : EventTarget {
    var onsuccess: ((Event) -> Any)?
    var onerror: ((Event) -> Any)?
    val result: Any?
}

external interface IDBFactory {
    fun open(name: String, version: Int = definedExternally): IDBOpenDBRequest
    fun deleteDatabase(name: String): IDBOpenDBRequest
}

external val indexedDB: IDBFactory