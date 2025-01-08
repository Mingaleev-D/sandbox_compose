package com.example.sandbox_compose.domain.usecase

import com.example.sandbox_compose.domain.model.AddressDomainModel
import com.example.sandbox_compose.domain.repository.OrderRepository

class PlaceOrderUseCase(
       private val orderRepository:OrderRepository
) {

    suspend fun executeOrder(addressDomainModel: AddressDomainModel) =
           orderRepository.placeOrder(addressDomainModel)
}
