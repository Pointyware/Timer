package com.taushsampley.timer.tasks

import java.util.concurrent.TimeUnit

/**
 * Describes a prior tracked task.
 *
 * Clean Layer: Entity
 *
 * @property title The name of the task.
 * @property elapsedTime The amount of time recorded for the task in seconds.
 * @property startTime The start time of the task, in milliseconds, using Unix epoch.
 * @property endTime The end time of the task, in milliseconds, using Unix epoch.
 */
data class Record(
    val title: String,
    val startTime: Long,
    val endTime: Long
) {
    val elapsedTime: Int
        get() = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime).toInt()
}
