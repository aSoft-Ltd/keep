package keep

abstract external class Storage {
    open val length: Int
    fun key(index: Int): String?
    fun removeItem(key: String)
    fun clear()
    fun getItem(key: String): String?
    fun setItem(key: String, value: String)
}