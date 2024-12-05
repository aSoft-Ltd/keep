package keep

import kotlinx.serialization.Serializable

@Serializable
data class Sort(
    val by: String,
    val order: Order
)