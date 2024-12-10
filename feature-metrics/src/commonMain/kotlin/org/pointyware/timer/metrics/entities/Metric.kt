package org.pointyware.timer.metrics.entities

/**
 * Holds information necessary to display compiled task durations.
 */
interface Metric {

    /**
     * Leaves have a height of 1. The height of a branch is 1 plus the height of its maximal child
     * height.
     */
    val height: Int

    /**
     * The total amount of time that this metric covers.
     */
    val duration: Long

    /**
     * Sub=metrics that together compose this metric.
     */
    val children: List<Metric>

    /**
     * Uniquely identifies this metric.
     */
    val id: Int

    companion object {

        private var newId = 0

        /**
         * Sequentially generates new ids that can be used by metrics.
         * @return A new id incremented from the previous, starting at 0.
         */
        @Synchronized
        fun autogenerateId(): Int {
            return newId++
        }
    }
}
