package com.example.sandbox_compose.ui.pages.home_products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.sandbox_compose.R
import com.example.sandbox_compose.domain.model.Product
import com.example.sandbox_compose.ui.navigator.UiProductModel
import com.example.sandbox_compose.ui.pages.home_products.components.productItemModel
import com.example.sandbox_compose.ui.theme.BlackGreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailsPage(
       modifier: Modifier = Modifier,
       product: UiProductModel,
       navController: NavController,
       detailViewModel: ProductDetailViewModel = koinViewModel(),
       onBackClicked: () -> Unit,
) {
    Column(
           modifier = modifier
               .fillMaxSize()
               .verticalScroll(rememberScrollState())
    ) {
        Row {
            Image(
                   painter = painterResource(id = R.drawable.round_arrow_back_ios_24),
                   contentDescription = null,
                   modifier = Modifier
                       .padding(16.dp)
                       .size(48.dp)
                       .clip(CircleShape)
                       .background(Color.LightGray.copy(alpha = 0.4f))
                       .padding(8.dp)
                       .clickable {
                           onBackClicked()
                       }
                   // .align(Alignment.TopStart)
            )
            Spacer(modifier = Modifier.weight(1f))

            Image(
                   painter = painterResource(id = R.drawable.baseline_favorite_24),
                   contentDescription = null,
                   modifier = Modifier
                       .padding(16.dp)
                       .size(48.dp)
                       .clip(CircleShape)
                       .background(Color.LightGray.copy(alpha = 0.4f))
                       .padding(8.dp)
                       .clickable {  }
                   // .align(Alignment.TopEnd)
            )
        }

        AsyncImage(
               model = product.image,
               contentDescription = null,
               modifier = Modifier
                   .padding(horizontal = 10.dp)
                   .weight(1f)
                   .clip(RoundedCornerShape(26.dp)),
               contentScale = ContentScale.FillBounds
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                       text = product.title,
                       style = MaterialTheme.typography.titleMedium.copy(
                              fontWeight = FontWeight.SemiBold,
                              fontSize = 28.sp
                       ),
                       modifier = Modifier
                           .padding(16.dp)
                           .weight(1f)
                )
                Text(
                       text = "$${product.price}",
                       style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                       modifier = Modifier.padding(16.dp),
                       color = MaterialTheme.colorScheme.primary
                )
            }
            Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(horizontal = 16.dp),
                   verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                       painter = painterResource(id = R.drawable.baseline_star_24),
                       contentDescription = null
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                       text = "4.5",
                       style = MaterialTheme.typography.bodySmall.copy(
                              fontWeight = FontWeight.SemiBold,
                              fontSize = 20.sp
                       ),
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                       text = "(10 Reviews)",
                       style = MaterialTheme.typography.bodySmall.copy(
                              fontWeight = FontWeight.SemiBold,
                              fontSize = 18.sp
                       ),
                       color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                   text = "Description",
                   style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
                   modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                   text = product.description,
                   style = MaterialTheme.typography.bodySmall.copy(fontSize = 20.sp),
                   minLines = 3,
                   maxLines = 6,
                   modifier = Modifier.padding(horizontal = 16.dp),
                   color = Color.Gray
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                   text = "Size",
                   style = MaterialTheme.typography.titleMedium,
                   modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                repeat(4) {
                    SizeItem(size = "${it + 1}", isSelected = it == 0) {}
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(horizontal = 16.dp)
            ) {
                Button(
                       onClick = { /*TODO*/ },
                       modifier = Modifier.weight(1f),
                       colors = ButtonDefaults.buttonColors(
                              containerColor = BlackGreen
                       )
                ) {
                    Text(text = "Buy Now")
                }
                Spacer(modifier = Modifier.size(8.dp))
                IconButton(
                       onClick = { /*TODO*/ },
                       modifier = Modifier.padding(horizontal = 16.dp),
                       colors = IconButtonDefaults.iconButtonColors()
                           .copy(containerColor = Color.LightGray.copy(alpha = 0.4f))
                ) {
                    Image(
                           painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                           contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun SizeItem(
       size: String,
       isSelected: Boolean,
       onClick: () -> Unit
) {
    Box(
           modifier = Modifier
               .padding(horizontal = 4.dp)
               .size(48.dp)
               .clip(RoundedCornerShape(8.dp))
               .border(
                      width = 1.dp,
                      color = Color.Gray,
                      shape = RoundedCornerShape(8.dp)
               )
               .background(
                      if (isSelected) BlackGreen else Color.Transparent
               )
               .padding(8.dp)
               .clickable { onClick() }
    ) {
        Text(
               text = size,
               style = MaterialTheme.typography.bodySmall.copy(
                      fontSize = 18.sp,
                      color = if (isSelected) Color.White else BlackGreen
               ),
               modifier = Modifier.align(Alignment.Center)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun ProductDetailsPagePreview() {
//    ProductDetailsPage(product = productItemModel)
//}
