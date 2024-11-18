package com.taushsampley.timer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200,

    surface = Purple700,
    onSurface = Color.White
)

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    secondary = Teal200,

    surface = Purple200,
    onSurface = Color.Black,
)

val TimerIcons = Icons.TwoTone
val TimerIconsAutoMirrored = Icons.AutoMirrored.TwoTone

@Composable
fun TimerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
