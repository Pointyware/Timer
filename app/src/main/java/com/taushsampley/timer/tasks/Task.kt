package com.taushsampley.timer.tasks

/**
 * Groups records under a single name.
 *
 * Clean Layer: Entity
 */
data class Task(
    override val title: String,
    val records: MutableList<Record> = mutableListOf()
): Node {

    /**
     * Tasks are considered leaf nodes and will always return `null` for children.
     */
    override val children: List<Node>? get() = null
}
