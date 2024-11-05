package com.taushsampley.timer.organizer

/**
 * Used for presentation of an organizational hierarchy.
 *
 * Clean Layer: Frameworks and Drivers
 */
data class OrganizerElement(
    val title: String,
    val type: Type,
    val level: Int = 0,
) {

    enum class Type {
        Branch,
        Leaf
    }
}
