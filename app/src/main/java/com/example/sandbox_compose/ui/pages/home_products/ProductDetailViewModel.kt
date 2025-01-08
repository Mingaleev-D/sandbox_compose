package com.example.sandbox_compose.ui.pages.home_products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.AddCartRequestModel
import com.example.sandbox_compose.domain.model.Product
import com.example.sandbox_compose.domain.usecase.GetProductsUseCase
import com.example.sandbox_compose.ui.navigator.UiProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
       private val productsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _detailsStorageFlow =
           MutableStateFlow<ProductsDetailsState>(ProductsDetailsState.Nothing)
    val detailsStorageFlow = _detailsStorageFlow.asStateFlow()

    fun addProductToCart(product: UiProductModel) {
        viewModelScope.launch {
            _detailsStorageFlow.value = ProductsDetailsState.Loading
            val result = productsUseCase.executeAddProductToCart(
                   AddCartRequestModel(
                          productId = product.id,
                          quantity = 1,
                          productName = product.title,
                          price = product.price,
                          userId = 1
                   )
            )

            when(result){
                is ResultWrapper.Failure -> _detailsStorageFlow.value = ProductsDetailsState.Error("Something went wrong")
                is ResultWrapper.Success -> _detailsStorageFlow.value = ProductsDetailsState.Success("Product added to cart")
            }
        }


    }


}

sealed interface ProductsDetailsState {
    object Loading : ProductsDetailsState
    object Nothing : ProductsDetailsState
    data class Success(
           val msg: String,
    ) : ProductsDetailsState

    data class Error(val message: String) : ProductsDetailsState
}
