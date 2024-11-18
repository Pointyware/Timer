package org.pointyware.timer.shared.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun colorScheme(isDarkTheme: Boolean, isDynamicTheme: Boolean): ColorScheme = staticColorScheme(isDarkTheme)