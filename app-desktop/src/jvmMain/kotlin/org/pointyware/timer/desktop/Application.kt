package org.pointyware.timer.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.koin.core.context.startKoin
import org.pointyware.timer.shared.TimerApp
import org.pointyware.timer.shared.di.sharedModule

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
            TimerApp()
        }
    }

    // shutdown logic
}
