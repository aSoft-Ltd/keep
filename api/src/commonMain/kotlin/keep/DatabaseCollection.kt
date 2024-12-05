package keep

interface DatabaseCollection {
    fun tables()
    fun get(id:String)
    fun delete()
}