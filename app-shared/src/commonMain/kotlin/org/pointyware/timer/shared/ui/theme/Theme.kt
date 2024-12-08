package org.pointyware.timer.shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.pointyware.timer.ui.theme.Purple200
import org.pointyware.timer.ui.theme.Purple500
import org.pointyware.timer.ui.theme.Purple700
import org.pointyware.timer.ui.theme.Teal200

val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200,

    surface = Purple700,
    onSurface = Color.White
)

val LightColorPalette = lightColorScheme(
    primary = Purple500,
    secondary = Teal200,

    surface = Purple200,
    onSurface = Color.Black,
)

fun staticColorScheme(isDarkTheme: Boolean): ColorScheme {
    return if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
}

@Composable
expect fun colorScheme(isDarkTheme: Boolean, isDynamicTheme: Boolean): ColorScheme

@Composable
fun TimerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme(darkTheme, isDynamicTheme),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
