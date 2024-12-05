package keep

import kollections.List
import koncurrent.Later

interface DataSource<T> {
    fun load(options:DataLoadOptions):Later<List<T>>
    fun load(uid:String):Later<T>
    fun delete(uid:String):Later<T>
    fun update(uid:String, data:T):Later<T>
    fun create(uid:String, data:T):Later<T>
}