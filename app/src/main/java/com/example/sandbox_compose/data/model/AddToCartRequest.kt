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
