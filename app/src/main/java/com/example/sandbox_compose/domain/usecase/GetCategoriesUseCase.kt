package com.example.sandbox_compose.domain.usecase

import com.example.sandbox_compose.domain.repository.ProductsRepository

class GetCategoriesUseCase(
       private val repository: ProductsRepository
) {

    suspend fun execute() = repository.getCategories()
}
