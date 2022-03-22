package com.taushsampley.timer

/**
 * Describes a prior tracked task.
 *
 * @property title The name of the task.
 * @property elapsedTime The amount of time recorded for the task in seconds.
 */
data class Task(
    val title: String,
    val elapsedTime: Int
)
