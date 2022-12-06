package com.example.thirtydaysofminimalism.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White

private val DarkColorPalette = darkColors(
    background = Pink900,
    surface = Pink700,
    onSurface = White,
    primary = Grey900,
    onPrimary = White,
    secondary = Grey100
)

private val LightColorPalette = lightColors(
    primary = OrangeDark50,
    primaryVariant = OrangeDark100,
    secondary = Grey900,
    background = Orange100,
    surface = Orange50,
    onSurface = Grey900
)

@Composable
fun ThirtyDaysOfMinimalismTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}