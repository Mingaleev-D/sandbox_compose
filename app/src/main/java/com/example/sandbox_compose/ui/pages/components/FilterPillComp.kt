package com.example.sandbox_compose.ui.pages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterPillComp(
       modifier: Modifier = Modifier,
       typeFil: FilterType,
       isSelected: Boolean,
       onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color.Yellow else Color.White
    // val textColor = if (isSelected) Color.Black else Color.White
    Box(
           modifier = modifier
               .clip(RoundedCornerShape(20.dp))
               .clickable {
                   onClick()
               }
               .background(backgroundColor)
               .then(
                      if (!isSelected) {
                          Modifier.border(
                                 (0.5).dp,
                                 color = Color.Gray,
                                 RoundedCornerShape(20.dp)
                          )
                      } else {
                          Modifier
                      }
               )
               .padding(vertical = 8.dp, horizontal = 16.dp),
           contentAlignment = Alignment.Center
    ) {
        Text(
               text = typeFil.text,
               color = Color.Black,
               fontSize = 14.sp,
               fontFamily = FontFamily.SansSerif,
               fontWeight = FontWeight.Bold
        )
    }
}

enum class FilterType(val text: String) {
    RELEVANCE("relevance"),
    POLARITY("popularity"),
    RELEASE_DATE("release-date")
}

@Preview(showBackground = true)
@Composable
private fun FilterPillCompPreview() {
    FilterPillComp(
           typeFil = FilterType.RELEASE_DATE,
           isSelected = true,
           onClick = {}
    )
}
