package com.taushsampley.timer.tasks

import org.pointyware.timer.entities.Node

/**
 * Groups tasks or other categories (subcategories).
 *
 * Clean Layer: Entity
 */
data class Category(
    override val title: String,
    override val id: Long
): Node
