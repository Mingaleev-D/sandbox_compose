package com.example.sandbox_compose.ui.pages.home_products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.Product
import com.example.sandbox_compose.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
       private val productsUseCase: GetProductsUseCase,
) : ViewModel() {

    private val _detailsStorageFlow =
           MutableStateFlow<ProductsDetailsState>(ProductsDetailsState.Loading)
    val detailsStorageFlow = _detailsStorageFlow.asStateFlow()

    fun fetchProductDetails(id: Int) = viewModelScope.launch {
        _detailsStorageFlow.value = ProductsDetailsState.Loading

        productsUseCase.execute(id)
            .let { result ->
                when (result) {
                    is ResultWrapper.Failure -> {
                        _detailsStorageFlow.value = ProductsDetailsState.Error(result.exception.message.toString())
                    }

                    is ResultWrapper.Success -> {
                        _detailsStorageFlow.value = ProductsDetailsState.Success(result.value.products.first())
                    }
                }
            }
    }
}

sealed interface ProductsDetailsState {
    object Loading : ProductsDetailsState
    data class Success(
           val productDetail: Product,
    ) : ProductsDetailsState

    data class Error(val message: String) : ProductsDetailsState
}
