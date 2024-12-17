package org.pointyware.timer.calendar.viewmodels

import kotlinx.coroutines.flow.StateFlow

interface ICalendarViewModel {
    val metrics: StateFlow<MonthMetrics?>
    fun selectMonthAndYear(month: Int, year: Int)
    fun goToPreviousMonth()
    fun goToNextMonth()
}

class CalendarViewModel(): ICalendarViewModel {
    override val metrics: StateFlow<MonthMetrics?>
        get() = TODO()

    override fun selectMonthAndYear(month: Int, year: Int) {
        TODO()
    }

    override fun goToPreviousMonth() {
        TODO()
    }

    override fun goToNextMonth() {
        TODO()
    }
}
