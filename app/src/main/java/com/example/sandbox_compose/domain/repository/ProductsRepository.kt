package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.CategoriesListModel
import com.example.sandbox_compose.domain.model.ProductListModel

interface ProductsRepository {

    suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>
}
