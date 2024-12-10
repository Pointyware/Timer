package com.taushsampley.timer.metrics

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.pointyware.timer.metrics.entities.TaskMetric

/**
 */
class TaskMetricTest {

    private val shortDuration = 5*1000L
    private val mediumDuration = 120*1000L
    private val longDuration = 8*3600*1000L

    private val shortTask = TaskMetric(shortDuration)
    private val mediumTask = TaskMetric(mediumDuration)
    private val longTask = TaskMetric(longDuration)

    @Test
    fun duration() {
        assertThat(shortTask.duration, `is`(shortDuration))
        assertThat(mediumTask.duration, `is`(mediumDuration))
        assertThat(longTask.duration, `is`(longDuration))
    }

    @Test
    fun height() {
        listOf(
            shortTask, mediumTask, longTask
        ).forEach {
            assertThat(it.height, `is`(1))
        }
    }

    @Test
    fun children() {
        listOf(shortTask, mediumTask, longTask).forEach {
            assertThat(it.children, `is`(emptyList()))
        }
    }
}
