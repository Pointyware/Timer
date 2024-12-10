package org.pointyware.timer.metrics.entities

/**
 * Holds metric data for a single task
 */
data class TaskMetric(
    override val duration: Long
): Metric {

    override val height: Int get() = 1

    override val children: List<Metric> get() = emptyList()

    override val id: Int = Metric.autogenerateId()
}
