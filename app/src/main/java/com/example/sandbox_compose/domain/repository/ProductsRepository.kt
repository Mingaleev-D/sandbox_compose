package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.data.model.ProductsItem
import com.example.sandbox_compose.data.remote.ResultWrapper

interface ProductsRepository {

    suspend fun getProducts(category: String?): ResultWrapper<List<ProductsItem>>
}
