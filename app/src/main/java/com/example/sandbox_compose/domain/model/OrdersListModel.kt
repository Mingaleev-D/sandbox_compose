package com.example.sandbox_compose.domain.model

data class OrdersListModel(
       val `data`: List<OrdersData>,
       val msg: String
)

data class OrdersData(
       val id: Int,
       val items: List<OrderProductItem>,
       val orderDate: String,
       val status: String,
       val totalAmount: Double,
       val userId: Int
)

data class OrderProductItem(
       val id: Int,
       val orderId: Int,
       val price: Double,
       val productId: Int,
       val productName: String,
       val quantity: Int,
       val userId: Int
)
