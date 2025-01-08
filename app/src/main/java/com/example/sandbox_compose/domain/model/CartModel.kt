package com.example.sandbox_compose.domain.model

data class CartModel(
       val data: List<CartItemModel>,
       val msg: String
)

data class CartItemModel(
       val id: Int,
       val productId: Int,
      // val userId: Int,
      // val name: String,
       val price: Double,
       val imageUrl: String?,
       val quantity: Int,
       val productName: String
)

data class CartSummary(
       val `data`: SummaryData,
       val msg: String
)

data class SummaryData(
       val discount: Double,
       val items: List<CartItemModel>,
       val shipping: Double,
       val subtotal: Double,
       val tax: Double,
       val total: Double
)
