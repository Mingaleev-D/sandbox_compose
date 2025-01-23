package com.example.sandbox_compose.ui.pages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.sandbox_compose.domain.model.demoGames

@Composable
fun GameListComp(
       modifier: Modifier = Modifier,
       title: String,
       postersImg: List<String>
) {
    Column(
           modifier = modifier,
    ) {
        Text(
               text = title,
               fontSize = 38.sp,
               style = TextStyle(
                      fontFamily = FontFamily.Cursive,
                      fontWeight = FontWeight.ExtraBold,
                      color = Color.White
               ),
               modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(
               contentPadding = PaddingValues(
                      horizontal = 10.dp,
                      vertical = 6.dp
               ),
               horizontalArrangement = Arrangement.spacedBy(16.dp),
               modifier = Modifier.fillMaxWidth(),
        ) {
            items(postersImg) { item ->
                PosterImage(imageUrl = item)
            }
        }
    }
}

@Composable
fun PosterImage(imageUrl: String) {
    Card(
           modifier = Modifier
               .width(275.dp)
               .height(175.dp),
           shape = RoundedCornerShape(12.dp),
           elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // Устанавливаем elevation
           colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        AsyncImage(
               model = ImageRequest.Builder(LocalContext.current)
                   .data(imageUrl)
                   .crossfade(true)
                   .build(),
               contentDescription = "",
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .fillMaxSize()
                   .clip(RoundedCornerShape(12.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GameListCompPreview() {
    GameListComp(
           title = "All Games",
           postersImg = demoGames.map { it.thumbnail }
    )
}
