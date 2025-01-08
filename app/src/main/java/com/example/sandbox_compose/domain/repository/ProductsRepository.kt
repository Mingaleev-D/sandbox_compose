package com.example.sandbox_compose.domain.repository

import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.AddCartRequestModel
import com.example.sandbox_compose.domain.model.CartItemModel
import com.example.sandbox_compose.domain.model.CartModel
import com.example.sandbox_compose.domain.model.CartSummary
import com.example.sandbox_compose.domain.model.CategoriesListModel
import com.example.sandbox_compose.domain.model.ProductListModel

interface ProductsRepository {

    suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel>
  //  suspend fun getProductsItem(id: Int): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>
    suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel>
    suspend fun getCart(): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Int): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary>
}
