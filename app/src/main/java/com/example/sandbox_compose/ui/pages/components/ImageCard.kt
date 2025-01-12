package com.example.sandbox_compose.ui.pages.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.sandbox_compose.domain.model.UnsplashImage

@Composable
fun ImageCard(
       modifier: Modifier = Modifier,
       image: UnsplashImage?
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(image?.imageUrlSmall)
        .crossfade(true)
        .build()
    val aspectRatio: Float by remember {
        derivedStateOf { (image?.width?.toFloat() ?: 1f) / (image?.height?.toFloat() ?: 1f) }
    }

    Card(
           shape = RoundedCornerShape(10.dp),
           modifier = Modifier
               .fillMaxWidth()
               .aspectRatio(aspectRatio)
               .then(modifier)
    ) {
        AsyncImage(
               model = imageRequest,
               contentDescription = null,
               contentScale = ContentScale.FillBounds,
               modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ImageCard(
       modifier: Modifier = Modifier,
       image: UnsplashImage?,
       isFavorite: Boolean,
       onToggleFavoriteStatus: () -> Unit
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(image?.imageUrlSmall)
        .crossfade(true)
        .build()
    val aspectRatio: Float by remember {
        derivedStateOf { (image?.width?.toFloat() ?: 1f) / (image?.height?.toFloat() ?: 1f) }
    }

    Card(
           // shape = RoundedCornerShape(16.dp),
           modifier = Modifier
               .fillMaxWidth()
               .shadow(
                      ambientColor = MaterialTheme.colorScheme.primary,
                      elevation = 6.dp,
                      shape = RoundedCornerShape(16.dp)
               )
               .aspectRatio(aspectRatio)
               .then(modifier),
    ) {
        Box {
            AsyncImage(
                   model = imageRequest,
                   contentDescription = null,
                   contentScale = ContentScale.FillBounds,
                   modifier = Modifier.fillMaxSize()
            )
            FavoriteButton(
                   isFavorite = isFavorite,
                   onClick = onToggleFavoriteStatus,
                   modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun FavoriteButton(
       modifier: Modifier = Modifier,
       isFavorite: Boolean,
       onClick: () -> Unit
) {
    FilledIconToggleButton(
           modifier = modifier,
           checked = isFavorite,
           onCheckedChange = { onClick() },
           colors = IconButtonDefaults.filledIconToggleButtonColors(
                  containerColor = Color.Transparent
           )
    ) {
        if (isFavorite) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = Color.Red)
        } else {
            Icon(
                   imageVector = Icons.Default.FavoriteBorder,
                   contentDescription = null,
                   tint = Color.Red
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun ImageCardPreview() {
//    ImageCard(
//           isFavorite = true,
//           image = sampleImage,
//           onToggleFavoriteStatus = {}
//    )
//}
