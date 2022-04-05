package com.taushsampley.timer.tasks

import java.util.concurrent.TimeUnit

/**
 * Describes a single instance of task tracking.
 *
 * Clean Layer: Entity
 *
 * @property startTime The start time of the task, in milliseconds, using Unix epoch.
 * @property endTime The end time of the task, in milliseconds, using Unix epoch.
 */
data class Record(
    val startTime: Long,
    val endTime: Long
) {
    /**
     * The amount of time recorded for the task in seconds.
     */
    val elapsedTime: Int
        get() = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime).toInt()
}
