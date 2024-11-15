package org.pointyware.timer.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

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

        }
    }

    // shutdown logic
}
