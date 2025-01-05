package com.example.sandbox_compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
       primary = BlackGreen,
       secondary = PurpleGrey80,
       tertiary = Pink80
)
private val LightColorScheme = lightColorScheme(
       primary = BlackGreen,
       secondary = PurpleGrey40,
       tertiary = Pink40
       /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Sandbox_composeTheme(
       darkTheme: Boolean = isSystemInDarkTheme(),
       // Dynamic color is available on Android 12+
       dynamicColor: Boolean = true,
       content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    //disable the theme Dynamic color is available on Android 12+
    //    val colorScheme = when {
    //        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
    //            val context = LocalContext.current
    //            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    //        }
    //        darkTheme -> DarkColorScheme
    //        else -> LightColorScheme
    //    }
    MaterialTheme(
           colorScheme = colorScheme,
           typography = AppTypography,
           content = content
    )
}
