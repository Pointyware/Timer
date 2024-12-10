package org.pointyware.timer.calendar.viewmodels


/**
 *
 * @property month An integer indicating month of the year, starting at 0 (January).
 * @property year An integer indicating the year CE.
 * @property metrics A list of lists of metric data.
 */
data class MonthMetrics(
    val month: Int,
    val year: Int,
    val metrics: List<List<DayMetrics>>
) {

}
