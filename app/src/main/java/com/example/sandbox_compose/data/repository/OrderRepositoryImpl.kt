package com.example.sandbox_compose.data.repository

import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.AddressDomainModel
import com.example.sandbox_compose.domain.model.OrdersListModel
import com.example.sandbox_compose.domain.repository.OrderRepository

class OrderRepositoryImpl(
       private val apiService: ApiService
) : OrderRepository {

    override suspend fun placeOrder(addressDomainModel: AddressDomainModel): ResultWrapper<Long> {
        return apiService.placeOrder(addressDomainModel, 1)
    }

    override suspend fun getOrderList(): ResultWrapper<OrdersListModel> {
        return apiService.getOrderList()
    }
}
