package org.pointyware.timer.entities

/**
 * Groups tasks or other categories (subcategories).
 *
 * Clean Layer: Entity
 */
data class Category(
    override val title: String,
    override val id: Long
): Node
