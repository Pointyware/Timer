package org.pointyware.timer.entities

import kotlinx.datetime.Instant

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
    val endTime: Long,
    val id: Long = 0
) {
    /**
     * The amount of time recorded for the task in seconds.
     */
    val elapsedTime: Int by lazy {
        Instant.fromEpochMilliseconds(endTime).epochSeconds.toInt() -
                Instant.fromEpochMilliseconds(startTime).epochSeconds.toInt()
    }
}
