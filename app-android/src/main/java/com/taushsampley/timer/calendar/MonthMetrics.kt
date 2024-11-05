package com.taushsampley.timer.calendar

import androidx.annotation.StringRes

/**
 *
 * @property month An integer indicating month of the year, starting at 0 (January).
 * @property year An integer indicating the year CE.
 * @property metrics A list of lists of metric data.
 */
data class MonthMetrics(
    @StringRes val month: Int,
    val year: Int,
    val metrics: List<List<DayMetrics>>
) {

}
