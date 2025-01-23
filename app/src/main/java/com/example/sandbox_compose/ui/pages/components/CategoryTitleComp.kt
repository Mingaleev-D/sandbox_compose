package com.example.sandbox_compose.ui.pages.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryTitleComp(
       modifier: Modifier = Modifier,
       txt: String
) {
    Text(
           text = txt,
           fontSize = 38.sp,
           style = TextStyle(
                  fontFamily = FontFamily.Cursive,
                  fontWeight = FontWeight.ExtraBold,
                  color = Color.White
           ),
           modifier = modifier.padding(start = 16.dp)
    )
}
