package com.example.sandbox_compose.data.repository

import com.example.sandbox_compose.data.remote.ApiService
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.AddCartRequestModel
import com.example.sandbox_compose.domain.model.CartItemModel
import com.example.sandbox_compose.domain.model.CartModel
import com.example.sandbox_compose.domain.model.CartSummary
import com.example.sandbox_compose.domain.model.CategoriesListModel
import com.example.sandbox_compose.domain.model.ProductListModel
import com.example.sandbox_compose.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
       private val apiService: ApiService
) : ProductsRepository {

    override suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel> {
        return apiService.getProducts(category = category)
    }

//    override suspend fun getProductsItem(id: Int): ResultWrapper<ProductListModel> {
//        return apiService.getProductsItem(id = id)
//    }

    override suspend fun getCategories(): ResultWrapper<CategoriesListModel> {
        return apiService.getCategories()
    }

    override suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel> {
        return apiService.addProductToCart(request = request)
    }

    override suspend fun getCart(): ResultWrapper<CartModel> {
        return apiService.getCart()
    }

    override suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel> {
        return apiService.updateQuantity(cartItemModel = cartItemModel)
    }

    override suspend fun deleteItem(
           cartItemId: Int,
           userId: Int
    ): ResultWrapper<CartModel> {
        return apiService.deleteItem(cartItemId = cartItemId, userId = userId)
    }

    override suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary> {
        return apiService.getCartSummary(userId = userId)
    }
}
