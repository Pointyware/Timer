package org.pointyware.timer.tasks.entities

/**
 * Groups records under a single name.
 *
 * Clean Layer: Entity
 */
data class Task(
    override val title: String,
    override val id: Long
): Node