package com.example.sandbox_compose.ui.pages.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.sandbox_compose.ui.pages.components.Dimens.IndicatorSize
import com.example.sandbox_compose.ui.theme.Blue
import com.example.sandbox_compose.ui.theme.BlueGray
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme

@Composable
fun PageIndicator(
       modifier: Modifier = Modifier,
       pagesSize: Int,
       selectedPage: Int,
       selectedColor: Color = Blue,
       unselectedColor: Color = BlueGray
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(times = pagesSize) { page ->
            Box(
                   modifier = Modifier
                       .size(IndicatorSize)
                       .clip(CircleShape)
                       .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}

@Preview(
       name = "Ночь-Night Mode",
       uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
       name = "День-Day Mode",
       uiMode = Configuration.UI_MODE_NIGHT_NO,
       showBackground = true
)
@Composable
private fun PageIndicatorPreview() {
    Sandbox_composeTheme { }
    PageIndicator(
           pagesSize = 3,
           selectedPage = 0
    )
}
