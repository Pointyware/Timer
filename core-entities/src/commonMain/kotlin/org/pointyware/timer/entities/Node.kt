package org.pointyware.timer.entities

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
     */
    val id: Long
}
