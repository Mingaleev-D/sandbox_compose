package com.example.sandbox_compose.data.model


import com.example.sandbox_compose.domain.model.CartModel
import com.example.sandbox_compose.domain.model.CartSummary
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCart(
       @SerialName("data")
    val `data`: List<RemoteCartItem> = listOf(),
       @SerialName("msg")
    val msg: String = ""
){
    fun toCartModel(): CartModel {
        return CartModel(
               data = data.map { it.toCartItemModel() },
               msg = msg
        )
    }
}

@Serializable
data class CartSummaryResponse(
       val `data`: Summary,
       val msg: String
) {
    fun toCartSummary() = CartSummary(
           data = `data`.toSummaryData(),
           msg = msg
    )
}
