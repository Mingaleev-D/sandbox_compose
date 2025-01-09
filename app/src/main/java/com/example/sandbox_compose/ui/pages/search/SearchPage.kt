package com.example.sandbox_compose.ui.pages.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchPage(
       onBackClick: () -> Unit
) {
    Box(
           modifier = Modifier.fillMaxSize(),
           contentAlignment = Alignment.Center
    ) {
        IconButton(
               modifier = Modifier.align(Alignment.CenterStart),
               onClick = onBackClick
        ) {
            Icon(
                   imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                   contentDescription = null
            )
        }
        Text(text = "Search Screen")
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPagePreview() {
    SearchPage(onBackClick = {})
}
