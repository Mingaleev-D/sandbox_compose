package com.example.sandbox_compose.ui.pages.home_products.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sandbox_compose.domain.model.Product

@Composable
fun HomeProductRow(
       products: List<Product>,
       title: String,
       onClick: (Product) -> Unit,
) {
    Column {
        Box(
               modifier = Modifier
                   .padding(horizontal = 16.dp)
                   .fillMaxWidth()
        ) {
            Text(
                   text = title,
                   style = MaterialTheme.typography.titleMedium,
                   modifier = Modifier.align(
                          Alignment.CenterStart
                   ),
                   fontWeight = FontWeight.SemiBold
            )
            Text(
                   text = "View all",
                   style = MaterialTheme.typography.bodyMedium,
                   color = MaterialTheme.colorScheme.primary,
                   modifier = Modifier.align(
                          Alignment.CenterEnd
                   )
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow {
            items(products) { product ->
                ProductItemView(product = product, onClick = onClick)
            }
        }
    }
}
