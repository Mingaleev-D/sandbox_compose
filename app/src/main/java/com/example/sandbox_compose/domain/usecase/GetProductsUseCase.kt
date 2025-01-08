package com.example.sandbox_compose.domain.usecase

import com.example.sandbox_compose.domain.repository.ProductsRepository

class GetProductsUseCase(
       private val repository: ProductsRepository
) {

    suspend fun execute(category: Int?) = repository.getProducts(category)
    suspend fun execute(id: Int) = repository.getProductsItem(id)
}
