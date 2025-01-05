package com.example.sandbox_compose.ui.pages.home_products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sandbox_compose.domain.model.Product
import com.example.sandbox_compose.domain.model.ProductListModel
import com.example.sandbox_compose.ui.components.LoadingState
import com.example.sandbox_compose.ui.pages.home_products.components.HomeProductRow
import com.example.sandbox_compose.ui.pages.home_products.components.ProfileHeader
import com.example.sandbox_compose.ui.pages.home_products.components.SearchBar
import com.example.sandbox_compose.ui.theme.BlackGreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
       modifier: Modifier = Modifier,
       navController: NavController,
       homeVM: HomeViewModel = koinViewModel(),
) {
    val uiState = homeVM.uiProductsState.collectAsState()

    LaunchedEffect(key1 = homeVM, block = { homeVM.getAllProducts() })

    Column(
           modifier = Modifier
               .fillMaxSize(),
           //           verticalArrangement = Arrangement.Center,
           //           horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState.value) {
            is HomeScreenUIEvents.Error -> {
                val error = (uiState.value as HomeScreenUIEvents.Error).error
                Text(text = error)
            }

            HomeScreenUIEvents.Loading -> {
                Column(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingState()
                }
            }

            is HomeScreenUIEvents.Success -> {
                val data = (uiState.value as HomeScreenUIEvents.Success)
                HomeContent(
                       featured = data.featured,
                       popularProducts = data.popularProducts,
                       popularProductsBooks = data.popularProductsBooks,
                       categories = data.categories,
                )
            }
        }
    }
}

@Composable
private fun HomeContent(
       featured: List<Product>,
       popularProducts: List<Product>,
       popularProductsBooks: List<Product>,
       categories: List<String>,
) {
    LazyColumn {
        item {
            ProfileHeader()
            Spacer(modifier = Modifier.size(12.dp))
            SearchBar(value = "", onTextChanged = {})
            Spacer(modifier = Modifier.size(16.dp))
        }
        item {
            if (categories.isNotEmpty()) {
                LazyRow {
                    items(categories) { category ->
                        Box(
                               modifier = Modifier
                                   .padding(8.dp)
                                   .clip(shape = RoundedCornerShape(8.dp))
                                   .border(
                                          width = 1.dp,
                                          color = BlackGreen,
                                          shape = RoundedCornerShape(8.dp)
                                   )
                                   .background(color = BlackGreen),
                               // colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                               //elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Text(
                                   text = category.apply { replaceFirstChar { it.uppercase() } },
                                   style = MaterialTheme.typography.bodyMedium,
                                   modifier = Modifier
                                       //.clip(RoundedCornerShape(8.dp))
                                       .padding(8.dp),
                                   // .background(MaterialTheme.colorScheme.primary),
                                   fontWeight = FontWeight.SemiBold,
                                   maxLines = 1,
                                   overflow = TextOverflow.Ellipsis,
                                   color = Color.White
                            )
                        }
                    }
                }
            }

            if (featured.isNotEmpty()) {
                HomeProductRow(products = featured, title = "Featured")
                Spacer(modifier = Modifier.size(16.dp))
            }
            if (popularProducts.isNotEmpty()) {
                HomeProductRow(products = popularProducts, title = "Popular Products")
                Spacer(modifier = Modifier.size(16.dp))
            }
            if (popularProducts.isNotEmpty()) {
                HomeProductRow(products = popularProductsBooks, title = "Popular Books")
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}
