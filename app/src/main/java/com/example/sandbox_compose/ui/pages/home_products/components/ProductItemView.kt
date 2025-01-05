package com.example.sandbox_compose.ui.pages.home_products.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.sandbox_compose.R
import com.example.sandbox_compose.domain.model.Product

@Composable
fun ProductItemView(
       product: Product,
       onClick: (Product) -> Unit,
) {
    Box(
           modifier = Modifier
               .padding(horizontal = 8.dp)
               .size(width = 126.dp, height = 164.dp)
               .clickable { onClick(product) },
           ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                   model = product.image,
                   contentDescription = null,
                   modifier = Modifier
                       // .fillMaxSize()
                       .clip(RoundedCornerShape(12.dp))
                       .height(106.dp),
                   contentScale = ContentScale.Crop,
                   error = painterResource(R.drawable.error_img)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                   text = product.title,
                   style = MaterialTheme.typography.bodyMedium,
                   modifier = Modifier.padding(horizontal = 8.dp),
                   fontWeight = FontWeight.SemiBold,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
            )
            Text(
                   text = "$${product.price}",
                   style = MaterialTheme.typography.bodySmall,
                   modifier = Modifier.padding(horizontal = 8.dp),
                   color = MaterialTheme.colorScheme.primary,
                   fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemViewPreview() {
    ProductItemView(product = productItemModel) {
    }
}

val productItemModel = Product(
       id = 1,
       title = "Product 1",
       price = 9.99,
       categoryId = 1,
       description = "Description 1",
       image = "https://fdn2.gsmarena.com/vv/bigpic/samsung-galaxy-tab-s7-1.jpg"
)
