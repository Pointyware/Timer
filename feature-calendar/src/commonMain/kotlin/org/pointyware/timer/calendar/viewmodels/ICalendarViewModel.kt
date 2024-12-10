package org.pointyware.timer.calendar.viewmodels

import kotlinx.coroutines.flow.StateFlow

interface ICalendarViewModel {
    val metrics: StateFlow<MonthMetrics?>
    fun selectMonthAndYear(month: Int, year: Int)
    fun goToPreviousMonth()
    fun goToNextMonth()
}
