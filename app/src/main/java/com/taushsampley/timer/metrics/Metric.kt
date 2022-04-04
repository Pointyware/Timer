package com.taushsampley.timer.metrics

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
}
