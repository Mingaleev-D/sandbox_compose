package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.AddressDomainModel
import com.example.sandbox_compose.domain.model.OrdersListModel

interface OrderRepository {
    suspend fun placeOrder(addressDomainModel: AddressDomainModel): ResultWrapper<Long>
    suspend fun getOrderList(): ResultWrapper<OrdersListModel>
}
