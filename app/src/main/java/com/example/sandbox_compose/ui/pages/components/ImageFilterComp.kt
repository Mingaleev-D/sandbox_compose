package com.example.sandbox_compose.ui.pages.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun ImageFilterComp(
       imageUrl: String,
       posterSize: GamePosterSize,
       onClick: () -> Unit,
) {
    val height = if (posterSize == GamePosterSize.SMALL) 100 else 205
    val width = if (posterSize == GamePosterSize.SMALL) 175 else 156
    AsyncImage(
           model = ImageRequest.Builder(LocalContext.current)
               .data(imageUrl)
               .crossfade(true)
               .build(),
           contentDescription = "poster",
           modifier = Modifier
               .padding(6.dp)
               .size(width = (width).dp, height = (height).dp)
               .clip(RoundedCornerShape(8.dp))
               .clickable {
                   onClick()
               },
           contentScale = ContentScale.FillBounds
    )

}

enum class GamePosterSize {
    SMALL,
    BIG
}
