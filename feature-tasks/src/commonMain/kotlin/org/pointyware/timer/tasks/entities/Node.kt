package org.pointyware.timer.tasks.entities

/**
 * Used to generalize a hierarchy of task elements.
 *
 * Clean Layer: Entity
 */
interface Node {
    /**
     * Human-readable text that describes this node.
     */
    val title: String

    /**
     * Used for database consistency.
     * TODO: remove from domain
     */
    val id: Long
}
