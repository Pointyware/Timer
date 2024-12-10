package org.pointyware.timer.metrics.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

/**
 * Produces random colors on demand.
 */
class RandomColorMap<K>: ColorMap<K> {

    private val colors: MutableMap<K, Color> = mutableMapOf()

    /**
     * Returns a full saturation color with the given hue.
     * @param hue float in the range [0, 1].
     */
    private fun hsv(hue: Float): Color {
        val hslArray = FloatArray(3) { 1.0f }
        hslArray[0] = hue * 360f
        hslArray[2] = 0.5f
        val colorInt = Color.hsl(hslArray[0], hslArray[1], hslArray[2]).toArgb()
        return Color(colorInt)
    }

    override fun get(key: K): Color {
        return colors[key] ?: hsv(Math.random().toFloat()).also {
            colors[key] = it
        }
    }
}
