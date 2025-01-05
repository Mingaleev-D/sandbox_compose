package com.example.sandbox_compose.ui.pages.home_products.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.sandbox_compose.data.model.ProductsItem

@Composable
fun ProductItemView(product: ProductsItem) {
    Card(
           modifier = Modifier
               .padding(horizontal = 8.dp)
               .size(width = 126.dp, height = 144.dp)
               .background(Color.Transparent),
           elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
           shape = RoundedCornerShape(16.dp),
           // colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = 0.1f))) {
            AsyncImage(
                   model = product.imageURL,
                   contentDescription = null,
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(95.dp),
                   contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                   text = product.title,
                   style = MaterialTheme.typography.bodyMedium,
                   modifier = Modifier.padding(horizontal = 8.dp),
                   fontWeight = FontWeight.SemiBold,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis,
                   color = MaterialTheme.colorScheme.primary
            )

            Text(
                   text = "$${product.price}",
                   style = MaterialTheme.typography.bodySmall,
                   modifier = Modifier.padding(horizontal = 8.dp),
                   color = MaterialTheme.colorScheme.primary,
                   fontWeight = FontWeight.SemiBold,
                   maxLines = 1,
            )
        }
    }
}
