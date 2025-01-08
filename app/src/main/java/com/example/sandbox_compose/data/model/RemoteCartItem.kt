package com.example.sandbox_compose.data.model


import com.example.sandbox_compose.domain.model.CartItemModel
import com.example.sandbox_compose.domain.model.SummaryData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCartItem(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("imageUrl")
    val imageUrl: String = "",
    @SerialName("price")
    val price: Double = 0.0,
    @SerialName("productId")
    val productId: Int = 0,
    @SerialName("productName")
    val productName: String = "",
    @SerialName("quantity")
    val quantity: Int = 0
){
    fun toCartItemModel(): CartItemModel {
        return CartItemModel(
               id = id,
               productId = productId,
               price = price,
               imageUrl = imageUrl,
               quantity = quantity,
               productName = productName,
              // userId = TODO(),
             //  name = TODO(),
        )
    }
}


@Serializable
data class Summary(
       val discount: Double,
       val items: List<RemoteCartItem>,
       val shipping: Double,
       val subtotal: Double,
       val tax: Double,
       val total: Double
) {
    fun toSummaryData() = SummaryData(
           discount = discount,
           items = items.map { it.toCartItemModel() },
           shipping = shipping,
           subtotal = subtotal,
           tax = tax,
           total = total
    )
}
