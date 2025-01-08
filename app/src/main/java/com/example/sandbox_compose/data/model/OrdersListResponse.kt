package com.example.sandbox_compose.data.model

import com.example.sandbox_compose.domain.model.OrderProductItem
import com.example.sandbox_compose.domain.model.OrdersData
import com.example.sandbox_compose.domain.model.OrdersListModel
import kotlinx.serialization.Serializable

@Serializable
data class OrdersListResponse(
       val `data`: List<OrderListData>,
       val msg: String
) {
    fun toDomainResponse(): OrdersListModel {
        return OrdersListModel(
               `data` = `data`.map { it.toDomainResponse() },
               msg = msg
        )
    }
}

@Serializable
data class OrderListData(
       val id: Int,
       val items: List<OrderItem>,
       val orderDate: String,
       val status: String,
       val totalAmount: Double,
       val userId: Int
) {
    fun toDomainResponse(): OrdersData {
        return OrdersData(
               id = id,
               items = items.map { it.toDomainResponse() },
               orderDate = orderDate,
               status = status,
               totalAmount = totalAmount,
               userId = userId
        )
    }
}

@Serializable
data class OrderItem(
       val id: Int,
       val orderId: Int,
       val price: Double,
       val productId: Int,
       val productName: String,
       val quantity: Int,
       val userId: Int
) {
    fun toDomainResponse(): OrderProductItem {
        return OrderProductItem(
               id = id,
               orderId = orderId,
               price = price,
               productId = productId,
               productName = productName,
               quantity = quantity,
               userId = userId
        )
    }
}

@Serializable
data class PlaceOrderResponse(
       val `data`: OrderD,
       val msg: String
)

@Serializable
data class OrderD(
       val id: Long
)
