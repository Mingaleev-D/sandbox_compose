package com.example.sandbox_compose.ui.pages.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sandbox_compose.R
import com.example.sandbox_compose.domain.model.UnsplashImage
import com.example.sandbox_compose.ui.pages.components.ImageTopAppBar
import com.example.sandbox_compose.ui.pages.components.ImagesVerticalGrid
import com.example.sandbox_compose.ui.pages.components.ZoomedImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
       modifier: Modifier = Modifier,
       images: List<UnsplashImage>,
       scrollBehavior: TopAppBarScrollBehavior,
       onImageClick: (String) -> Unit,
       onSearchClick: () -> Unit,
       onFABClick: () -> Unit
) {
    var showImagePreview by remember { mutableStateOf(false) }
    var activeImage by remember { mutableStateOf<UnsplashImage?>(null) }
    Box(
           modifier = modifier.fillMaxSize()
    ) {
        Column(
               modifier = Modifier.fillMaxSize(),
               horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageTopAppBar(
                   scrollBehavior = scrollBehavior,
                   onSearchClick = onSearchClick
            )
            ImagesVerticalGrid(
                   images = images,
                   onImageClick = onImageClick,
                   onImageDragStart = { image ->
                       activeImage = image
                       showImagePreview = true
                   },
                   onImageDragEnd = {showImagePreview = false}
            )
        }
        FloatingActionButton(
               onClick = onFABClick,
               modifier = Modifier
                   .align(Alignment.BottomEnd)
                   .padding(24.dp),
               // containerColor = Color.White
        ) {
            Icon(
                   painter = painterResource(R.drawable.baseline_save_24),
                   contentDescription = null,
                   tint = MaterialTheme.colorScheme.onBackground
            )
        }

        ZoomedImageCard(
               modifier = Modifier.padding(20.dp),
               isVisible = showImagePreview,
               image = activeImage
        )
    }
}
