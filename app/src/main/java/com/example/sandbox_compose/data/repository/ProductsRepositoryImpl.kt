package com.example.sandbox_compose.data.repository

import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.CategoriesListModel
import com.example.sandbox_compose.domain.model.ProductListModel
import com.example.sandbox_compose.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
       private val apiService: ApiService
) : ProductsRepository {

    override suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel> {
        return apiService.getProducts(category = category)
    }

    override suspend fun getProductsItem(id: Int): ResultWrapper<ProductListModel> {
        return apiService.getProductsItem(id = id)
    }

    override suspend fun getCategories(): ResultWrapper<CategoriesListModel> {
        return apiService.getCategories()
    }
}
