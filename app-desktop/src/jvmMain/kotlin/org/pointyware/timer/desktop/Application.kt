package org.pointyware.timer.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.koin.core.context.startKoin
import org.pointyware.timer.calendar.viewmodels.CalendarViewModelImpl
import org.pointyware.timer.metrics.viewmodels.MetricsViewModelImpl
import org.pointyware.timer.organizer.viewmodels.OrganizerViewModelImpl
import org.pointyware.timer.shared.TimerApp
import org.pointyware.timer.shared.di.sharedModule
import org.pointyware.timer.tasks.viewmodels.TimerViewModelImpl

/**
 *
 */
fun main(vararg args: String) {
    // startup logic

    startKoin {
        modules(
            sharedModule()
        )
    }

    application(exitProcessOnExit = false) {

        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            title = "Timer",
            state = windowState
        ) {
            TimerApp( // TODO: remove from composable and create on each navigation event
                timerViewModel = TimerViewModelImpl(),
                organizerViewModel = OrganizerViewModelImpl(),
                calendarViewModel = CalendarViewModelImpl(),
                metricsViewModel = MetricsViewModelImpl()
            )
        }
    }

    // shutdown logic
}
