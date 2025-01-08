package com.example.sandbox_compose.data.model

import com.example.sandbox_compose.domain.model.AddCartRequestModel
import com.example.sandbox_compose.domain.model.CartItemModel
import com.example.sandbox_compose.domain.model.CartModel
import com.example.sandbox_compose.domain.model.CartSummary
import com.example.sandbox_compose.domain.model.SummaryData
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequest(
       val productId: Int,
       val quantity: Int
) {
    companion object {
        fun fromCartRequestModel(addCartRequestModel: AddCartRequestModel) = AddToCartRequest(
               productId = addCartRequestModel.productId,
               quantity = addCartRequestModel.quantity,
        )
    }
}

//@Serializable
//class CartItem(
//       val id: Int,
//       val productId: Int,
//       val userId: Int,
//       val name: String,
//       val price: Double,
//       val imageUrl: String?,
//       val quantity: Int,
//       val productName: String
//) {
//    fun toCartItemModel(): CartItemModel {
//        return CartItemModel(
//               id = id,
//               productId = productId,
//               userId = userId,
//               name = name,
//               price = price,
//               imageUrl = imageUrl,
//               quantity = quantity,
//               productName = productName
//        )
//    }
//}
//
//@Serializable
//data class CartResponse(
//       val data: List<CartItem>,
//       val msg: String
//) {
//    fun toCartModel(): CartModel {
//        return CartModel(
//               data = data.map { it.toCartItemModel() },
//               msg = msg
//        )
//    }
//}
//
//@Serializable
//data class CartSummaryResponse(
//       val `data`: Summary,
//       val msg: String
//) {
//    fun toCartSummary() = CartSummary(
//           data = `data`.toSummaryData(),
//           msg = msg
//    )
//}
//
//@Serializable
//data class Summary(
//       val discount: Double,
//       val items: List<CartItem>,
//       val shipping: Double,
//       val subtotal: Double,
//       val tax: Double,
//       val total: Double
//) {
//    fun toSummaryData() = SummaryData(
//           discount = discount,
//           items = items.map { it.toCartItemModel() },
//           shipping = shipping,
//           subtotal = subtotal,
//           tax = tax,
//           total = total
//    )
//}
