package com.example.sandbox_compose.ui.pages.home_products

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.sandbox_compose.data.model.ProductsItem
import com.example.sandbox_compose.ui.pages.home_products.components.HomeProductRow
import com.example.sandbox_compose.ui.pages.home_products.components.ProfileHeader
import com.example.sandbox_compose.ui.pages.home_products.components.SearchBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
       modifier: Modifier = Modifier,
       navController: NavController,
       homeVM: HomeViewModel = koinViewModel(),
) {
    val uiState = homeVM.uiProductsState.collectAsState()
    LaunchedEffect(key1 = homeVM, block = { homeVM.getAllProducts() })

    Scaffold(
           modifier = modifier
    ) {
        Surface(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(paddingValues = it)
        ) {
            when (uiState.value) {
                is HomeScreenUIEvents.Error -> {
                    val error = (uiState.value as HomeScreenUIEvents.Error).error
                    Text(text = error)
                }

                HomeScreenUIEvents.Loading -> {
                    CircularProgressIndicator()
                }

                is HomeScreenUIEvents.Success -> {
                    val data = (uiState.value as HomeScreenUIEvents.Success)
                    HomeContent(
                           featured = data.featured,
                           popularProducts = data.popularProducts,
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeContent(
       featured: List<ProductsItem>,
       popularProducts: List<ProductsItem>,
) {
    LazyColumn {
        item {
            ProfileHeader()
            Spacer(modifier = Modifier.size(12.dp))
            SearchBar(value = "", onTextChanged = {})
            Spacer(modifier = Modifier.size(16.dp))
        }
        item {
            if (featured.isNotEmpty()) {
                HomeProductRow(products = featured, title = "Featured")
                Spacer(modifier = Modifier.size(16.dp))
            }
            if (popularProducts.isNotEmpty()) {
                HomeProductRow(products = popularProducts, title = "Popular Products")
                Spacer(modifier = Modifier.size(16.dp))
            }

        }
    }
}
