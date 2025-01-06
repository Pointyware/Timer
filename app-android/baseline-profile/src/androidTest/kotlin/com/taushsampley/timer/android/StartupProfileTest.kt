package com.taushsampley.timer.android

import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 *
 */
class StartupProfileTest {

    @get:Rule
    val baselineProfileRule: BaselineProfileRule = BaselineProfileRule()

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun start_task_view_calendar_stop_task() = baselineProfileRule.collect(
        packageName = "org.pointyware.timer",
    ) {
        pressHome()

        startActivityAndWait()

        /*
        Open Application; Should be on Timer Screen
        Start Timer With Default Task
         */

        /*
        Navigate to Calendar Screen on Bottom Navigation
         */

        /*
        Navigate back to Timer Screen with Back Gesture
        Stop Timer
        Navigate to Metrics Screen
        Stop Test
         */
    }
}
