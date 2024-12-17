package org.pointyware.timer.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.pointyware.timer.calendar.viewmodels.CalendarViewModel
import org.pointyware.timer.metrics.viewmodels.MetricsViewModel
import org.pointyware.timer.organizer.viewmodels.OrganizerViewModel
import org.pointyware.timer.shared.TimerApp
import org.pointyware.timer.tasks.viewmodels.TimerViewModel

/**
 *
 */
fun main(vararg args: String) {
    // startup logic

    application(exitProcessOnExit = false) {

        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            title = "Timer",
            state = windowState
        ) {
            TimerApp( // TODO: remove from composable and create on each navigation event
                timerViewModel = TimerViewModel(),
                organizerViewModel = OrganizerViewModel(),
                calendarViewModel = CalendarViewModel(),
                metricsViewModel = MetricsViewModel()
            )
        }
    }

    // shutdown logic
}
