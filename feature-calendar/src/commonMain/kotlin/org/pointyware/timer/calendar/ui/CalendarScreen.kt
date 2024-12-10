package org.pointyware.timer.calendar.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.pointyware.timer.calendar.viewmodels.ICalendarViewModel

/**
 */
@Composable
fun CalendarScreen(calendarViewModel: ICalendarViewModel) {

    Column {

//        val month by calendarViewModel.month.collectAsState()
//        val year by calendarViewModel.year.collectAsState()
        val metrics by calendarViewModel.metrics.collectAsState()

        Calendar(metrics)


    }

}
