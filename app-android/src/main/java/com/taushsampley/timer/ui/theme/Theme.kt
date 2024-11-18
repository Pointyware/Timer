package com.taushsampley.timer.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

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
fun TimerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (isDynamicTheme && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (darkTheme) {
            dynamicDarkColorScheme(LocalContext.current)
        } else {
            dynamicLightColorScheme(LocalContext.current)
        }
    } else {
        if (darkTheme) {
            DarkColorPalette
        } else {
            LightColorPalette
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
