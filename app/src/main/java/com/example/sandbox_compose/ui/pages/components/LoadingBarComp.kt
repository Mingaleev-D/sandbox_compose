package com.example.sandbox_compose.ui.pages.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingBarComp(
       modifier: Modifier = Modifier,
       size: Dp = 80.dp
) {

    val animation = rememberInfiniteTransition(label = "Loading Bar")
    val progress by animation.animateFloat(
           initialValue = 0f,
           targetValue = 1f,
           animationSpec = infiniteRepeatable(
                  animation = tween(durationMillis = 1000),
                  repeatMode = RepeatMode.Restart
           ),
           label = ""
    )
    Box(
           modifier = modifier
               .size(size)
               .scale(progress)
               .alpha(1f - progress)
               .border(
                      width = 5.dp,
                      color = Color.Red,
                      shape = CircleShape
               )
    )
}
