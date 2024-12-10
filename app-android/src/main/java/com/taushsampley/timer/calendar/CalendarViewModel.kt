package com.taushsampley.timer.calendar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.timer.calendar.viewmodels.ICalendarViewModel
import org.pointyware.timer.calendar.viewmodels.MonthMetrics

class CalendarViewModel: ViewModel(), ICalendarViewModel {

    private val _metrics = MutableStateFlow<MonthMetrics?>(null)
    override val metrics: StateFlow<MonthMetrics?> = _metrics

    /**
     * Load metrics data for the given month in the given year.
     */
    override fun selectMonthAndYear(month: Int, year: Int) {
        TODO("request data for given month and year")
    }

    /**
     * Load metrics data for month before the current metrics.
     */
    override fun goToPreviousMonth() {
        TODO("request data for previous month")
    }

    /**
     * Load metrics data for month after the current metrics.
     */
    override fun goToNextMonth() {
        TODO("request data for next month")
    }
}
