package com.example.sandbox_compose.ui.pages.home_products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sandbox_compose.data.model.ProductsItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
       modifier: Modifier = Modifier,
       navController: NavController,
       homeVM: HomeViewModel = koinViewModel(),
) {
    val uiState = homeVM.uiProductsState.collectAsState()
    LaunchedEffect(key1 = homeVM, block = { homeVM.getProducts() })
    when (uiState.value) {
        is HomeScreenUIEvents.Error -> {
            val error = (uiState.value as HomeScreenUIEvents.Error).error
            Text(text = error)
        }

        HomeScreenUIEvents.Loading -> {
            CircularProgressIndicator()
        }

        is HomeScreenUIEvents.Success -> {
            val dataList = (uiState.value as HomeScreenUIEvents.Success).productsList
            LazyColumn { ->
                items(dataList) {
                    ProductItemView(product = it)
                }
            }
        }
    }
}

@Composable
private fun ProductItemView(product: ProductsItem) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
               modifier = Modifier
                      .padding(16.dp)
                      .fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "$${product.price}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
