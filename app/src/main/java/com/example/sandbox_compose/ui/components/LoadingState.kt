package com.example.sandbox_compose.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable fun LoadingState() {
    CircularProgressIndicator(
           modifier = Modifier
               .fillMaxSize()
               .padding(all = 128.dp),
           color = Color.Green
    )
}
