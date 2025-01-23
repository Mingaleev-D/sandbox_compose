package com.example.sandbox_compose.ui.pages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
fun HeaderGamesComp() {
    Row(
           horizontalArrangement = Arrangement.Center,
           modifier = Modifier
               .fillMaxWidth()
               .padding(6.dp)
    ) {
        Text(
               text = "FREETOGAMES",
               style = TextStyle(
                      fontFamily = FontFamily.Cursive,
                      fontWeight = FontWeight.Bold,
                      color = Color.White,
                      fontSize = 28.sp
               ),
        )
    }
}
