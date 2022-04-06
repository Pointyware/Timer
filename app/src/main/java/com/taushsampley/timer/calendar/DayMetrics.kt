package com.taushsampley.timer.calendar

import com.taushsampley.timer.metrics.Metric

/**
 * @property day The day of some month/year that this data represents.
 * @property metrics A list of the metric entities for a specific day.
 */
data class DayMetrics(
    val day: Int?,
    val metrics: List<Metric>?
)
