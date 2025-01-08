package com.example.sandbox_compose.domain.usecase

import com.example.sandbox_compose.domain.model.AddCartRequestModel
import com.example.sandbox_compose.domain.model.CartItemModel
import com.example.sandbox_compose.domain.repository.ProductsRepository

class GetProductsUseCase(
       private val repository: ProductsRepository
) {

    suspend fun execute(category: Int?) = repository.getProducts(category)
   // suspend fun execute(id: Int) = repository.getProductsItem(id)

    //cart fun
    suspend fun executeAddProductToCart(request: AddCartRequestModel) = repository.addProductToCart(request)
    suspend fun executeGetCart() = repository.getCart()
    suspend fun executeCartSummary(userId: Int) = repository.getCartSummary(userId)
    suspend fun executeDelete(cartItemId: Int, userId: Int) = repository.deleteItem(cartItemId, userId)
    suspend fun executeUpdate(cartItemModel: CartItemModel) = repository.updateQuantity(cartItemModel)
}
