package com.taushsampley.timer.calendar

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.taushsampley.timer.ui.theme.TimerTheme

/**
 */
@Composable
fun CalendarScreen(calendarViewModel: CalendarViewModel) {

    Column {

//        val month by calendarViewModel.month.collectAsState()
//        val year by calendarViewModel.year.collectAsState()
        val metrics by calendarViewModel.metrics.collectAsState()

        Calendar(metrics)


    }

}

@Preview(showBackground = true,)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CalendarScreenPreview() {

    val viewModel = CalendarViewModel()

    TimerTheme {
        CalendarScreen(viewModel)
    }
}
