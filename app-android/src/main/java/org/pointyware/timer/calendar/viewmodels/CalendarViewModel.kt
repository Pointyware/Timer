package org.pointyware.timer.calendar.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(): ViewModel(), ICalendarViewModel {

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
