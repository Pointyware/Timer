package com.taushsampley.timer.metrics

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 */
class CategoryMetricTest {

    private val taskA = TaskMetric(3000)
    private val taskB = TaskMetric(2300)
    private val taskC = TaskMetric(50600)
    private val taskD = TaskMetric(23439040)
    private val taskE = TaskMetric(23903)

    private val categoryAB = CategoryMetric(taskA, taskB)
    private val categoryCD = CategoryMetric(taskC, taskD)
    private val categoryAbCd = CategoryMetric(categoryAB, categoryCD)
    private val categoryAbCdE = CategoryMetric(categoryAbCd, taskE)

    @Test
    fun constructor() {
        assertThat(categoryAB, `is`(CategoryMetric(listOf(taskA, taskB))))
        assertThat(categoryCD, `is`(CategoryMetric(listOf(taskC, taskD))))
        assertThat(categoryAbCd, `is`(CategoryMetric(listOf(categoryAB, categoryCD))))
        assertThat(categoryAbCdE, `is`(CategoryMetric(listOf(categoryAbCd, taskE))))
    }

    @Test
    fun duration() {
        val durationAb = taskA.duration + taskB.duration
        assertThat(categoryAB.duration, `is`(durationAb))

        val durationCd = taskC.duration + taskD.duration
        assertThat(categoryCD.duration, `is`(durationCd))

        val durationAbCd = durationAb + durationCd
        assertThat(categoryAbCd.duration, `is`(durationAbCd))

        val durationAbCdE = durationAbCd + taskE.duration
        assertThat(categoryAbCdE.duration, `is`(durationAbCdE))
    }

    @Test
    fun height() {
        assertThat(categoryAB.height, `is`(2))
        assertThat(categoryCD.height, `is`(2))
        assertThat(categoryAbCd.height, `is`(3))
        assertThat(categoryAbCdE.height, `is`(4))
    }

    @Test
    fun children() {
        assertThat(categoryAB.children, `is`(listOf(taskA, taskB)))
        assertThat(categoryCD.children, `is`(listOf(taskC, taskD)))
        assertThat(categoryAbCd.children, `is`(listOf(categoryAB, categoryCD)))
        assertThat(categoryAbCdE.children, `is`(listOf(categoryAbCd, taskE)))
    }
}
