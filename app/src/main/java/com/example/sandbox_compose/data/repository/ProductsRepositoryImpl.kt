package com.example.sandbox_compose.data.repository

import com.example.sandbox_compose.data.model.ProductsItem
import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
       private val apiService: ApiService
) : ProductsRepository {

    override suspend fun getProducts(): ResultWrapper<List<ProductsItem>> {
        return apiService.getProducts()
    }
}
