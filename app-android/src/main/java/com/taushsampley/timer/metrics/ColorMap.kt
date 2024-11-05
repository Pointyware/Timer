package com.taushsampley.timer.metrics

import androidx.compose.ui.graphics.Color

/**
 * Returns a color for a given id.
 */
interface ColorMap<K> {
    operator fun get(key: K): Color
}
