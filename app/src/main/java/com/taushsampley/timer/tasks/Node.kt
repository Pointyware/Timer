package com.taushsampley.timer.tasks

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
     * The nodes belonging to this node.
     */
    val children: List<Node>?
}
