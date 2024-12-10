package com.taushsampley.timer.metrics

import org.pointyware.timer.metrics.entities.Metric

/**
 * Holds metric data for a task category
 */
data class CategoryMetric(
    override val children: List<Metric>
): Metric {

    constructor(vararg children: Metric): this(children.asList())

    override val height: Int
        get() = children.maxOf { it.height } + 1

    override val duration: Long = children.sumOf { it.duration }

    override val id: Int = Metric.autogenerateId()
}
