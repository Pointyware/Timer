package com.taushsampley.timer.tasks

/**
 * Groups tasks or other categories (subcategories).
 *
 * Clean Layer: Entity
 */
data class Category(
    override val title: String,
    override val children: List<Node>
): Node
