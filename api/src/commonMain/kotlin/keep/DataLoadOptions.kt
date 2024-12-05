package keep

data class DataLoadOptions (
    val page: Int,
    val limit: Int,
    val key: String?,
    val sorts: List<Sort>? = emptyList()
)