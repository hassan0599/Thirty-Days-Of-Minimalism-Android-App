package com.example.thirtydaysofminimalism.color

import androidx.compose.ui.graphics.Color
import kotlin.math.min

class ColorChanger(private val color: Color) {
    fun lighten(fraction: Float): Color {
        val newRedValue = lightenColor(color.red, fraction)
        val newGreenValue = lightenColor(color.green, fraction)
        val newBlueValue = lightenColor(color.blue, fraction)
        return Color(newRedValue, newGreenValue, newBlueValue, color.alpha)
    }

    private fun lightenColor(colorComponent: Float, fraction: Float): Float {
        return min(colorComponent + (colorComponent * fraction), 1.0F)
    }
}