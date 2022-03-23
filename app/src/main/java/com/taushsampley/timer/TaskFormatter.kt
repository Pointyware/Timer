package com.taushsampley.timer

import java.util.concurrent.TimeUnit


/**
 * Formats a task's elapsed time as a duration string.
 *
 * Clean Layer: UI
 */
object TaskFormatter {

    fun durationStringForTask(task: Task): String {

        val taskTime = task.elapsedTime

        val remainingMinutes = taskTime / 60
        val displaySeconds = taskTime % 60

        val displayHours = remainingMinutes / 60
        val displayMinutes = remainingMinutes % 60

        return if (displayHours > 0) {
            "%02d:%02d:%02d".format(displayHours, displayMinutes, displaySeconds)
        } else {
            "%02d:%02d".format(displayMinutes, displaySeconds)
        }
    }
}