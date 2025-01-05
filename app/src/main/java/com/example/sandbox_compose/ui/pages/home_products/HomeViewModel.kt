package com.example.sandbox_compose.ui.pages.home_products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.model.CategoriesListModel
import com.example.sandbox_compose.domain.model.Product
import com.example.sandbox_compose.domain.usecase.GetCategoriesUseCase
import com.example.sandbox_compose.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
       private val productsUseCase: GetProductsUseCase,
       private val categoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

    private val _uiProductsState =
           MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiProductsState = _uiProductsState.asStateFlow()

    //    init {
    //        getAllProducts()
    //    }
    private suspend fun getProducts(category: Int?): List<Product> {
        productsUseCase.execute(category).let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return emptyList()
                }

                is ResultWrapper.Success -> {
                    return result.value.products
                }
            }
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            _uiProductsState.value = HomeScreenUIEvents.Loading
            val featured = getProducts(1)
            val popularProducts = getProducts(2)
            val popularProductsBooks = getProducts(4)
            val categories = getCategories()
            if (featured.isEmpty() && popularProducts.isEmpty() && popularProductsBooks.isEmpty() && categories.isNotEmpty()) {
                _uiProductsState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }
            _uiProductsState.value = HomeScreenUIEvents.Success(
                   featured,
                   popularProducts,
                   popularProductsBooks,
                   categories
            )
        }
    }

    private suspend fun getCategories(): List<String> {
        categoriesUseCase.execute().let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return emptyList()
                }

                is ResultWrapper.Success -> {
                    return result.value.categories.map { it.title }
                }
            }
        }
    }
}

sealed class HomeScreenUIEvents {
    object Loading : HomeScreenUIEvents()
    data class Success(
           val featured: List<Product>,
           val popularProducts: List<Product>,
           val popularProductsBooks: List<Product>,
           val categories: List<String>,
    ) : HomeScreenUIEvents()

    data class Error(val error: String) : HomeScreenUIEvents()
}
