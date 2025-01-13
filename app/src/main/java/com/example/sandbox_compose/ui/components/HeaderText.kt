package com.example.sandbox_compose.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HeaderText(
       text: String,
       modifier: Modifier = Modifier,
) {
    Text(
           text = text,
           style = MaterialTheme.typography.displayMedium,
           fontWeight = FontWeight.Bold,
           modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun HeaderTextPreview() {
    HeaderText(
           text = "Header Text"
    )
}
