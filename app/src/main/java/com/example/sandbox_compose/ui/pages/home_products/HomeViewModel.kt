package com.example.sandbox_compose.ui.pages.home_products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.data.model.ProductsItem
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
       private val productsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiProductsState =
           MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiProductsState = _uiProductsState.asStateFlow()

    fun getProducts() {
        viewModelScope.launch {
            _uiProductsState.value = HomeScreenUIEvents.Loading
            productsUseCase.execute().let { result ->
                when (result) {
                    is ResultWrapper.Failure -> {
                        val ex = result.exception
                        _uiProductsState.value = HomeScreenUIEvents.Error(ex.message.toString())
                    }

                    is ResultWrapper.Success -> {
                        val data = result.value
                        _uiProductsState.value = HomeScreenUIEvents.Success(data)
                    }
                }
            }
        }
    }
}

sealed class HomeScreenUIEvents {
    object Loading : HomeScreenUIEvents()
    data class Success(val productsList: List<ProductsItem>) : HomeScreenUIEvents()
    data class Error(val error: String) : HomeScreenUIEvents()
}
