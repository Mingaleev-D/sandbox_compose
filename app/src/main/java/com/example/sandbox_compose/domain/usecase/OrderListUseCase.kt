package com.example.sandbox_compose.domain.usecase

import com.example.sandbox_compose.domain.repository.OrderRepository

class OrderListUseCase(
       private val repository: OrderRepository
) {
    suspend fun execute() = repository.getOrderList()
}
