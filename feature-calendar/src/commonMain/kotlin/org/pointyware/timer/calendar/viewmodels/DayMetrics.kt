package org.pointyware.timer.calendar.viewmodels

import org.pointyware.timer.metrics.entities.Metric

/**
 * @property day The day of some month/year that this data represents.
 * @property metrics A list of the metric entities for a specific day.
 */
data class DayMetrics(
    val day: Int?,
    val metrics: List<Metric>?
)
