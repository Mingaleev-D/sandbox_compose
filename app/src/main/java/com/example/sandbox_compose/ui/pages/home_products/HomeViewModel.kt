package com.example.sandbox_compose.ui.pages.home_products



import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandbox_compose.data.model.ProductsItem
import com.example.sandbox_compose.data.remote.ResultWrapper
import com.example.sandbox_compose.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val category = "men's clothing"
val encodedCategory = Uri.encode(category)

val categoryJson = Json.encodeToString("men's clothing")


class HomeViewModel(
       private val productsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiProductsState =
           MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiProductsState = _uiProductsState.asStateFlow()
    //    init {
    //        getAllProducts()
    //    }
    private suspend fun getProducts(category: String?): List<ProductsItem> {
        productsUseCase.execute(category).let { result ->
            when (result) {
                is ResultWrapper.Failure -> {
                    return emptyList()
                }

                is ResultWrapper.Success -> {
                    return result.value
                }
            }
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            _uiProductsState.value = HomeScreenUIEvents.Loading
            val featured = getProducts("electronics")
            val popularProducts = getProducts("jewelery")
            if (featured.isEmpty() || popularProducts.isEmpty()) {
                _uiProductsState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }
            _uiProductsState.value = HomeScreenUIEvents.Success(featured, popularProducts)
        }
    }
}

sealed class HomeScreenUIEvents {
    object Loading : HomeScreenUIEvents()
    data class Success(
           val featured: List<ProductsItem>,
           val popularProducts: List<ProductsItem>,
    ) : HomeScreenUIEvents()

    data class Error(val error: String) : HomeScreenUIEvents()
}
