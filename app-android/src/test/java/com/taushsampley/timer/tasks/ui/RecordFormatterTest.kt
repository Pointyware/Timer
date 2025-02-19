package com.taushsampley.timer.tasks.ui

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.pointyware.timer.entities.Record
import org.pointyware.timer.tasks.ui.RecordFormatter


/**
 */
class RecordFormatterTest {

    private fun createRecord(hour: Int, minute: Int, second: Int): Record {
        val start = System.currentTimeMillis()
        val minutes = hour * 60 + minute
        val duration = minutes * 60 + second
        val millis = duration*1000
        return Record(start, start + millis.toLong())
    }

    @Test
    fun timeTextForTask() {
        listOf(
            Triple(0, 1, 0),
            Triple(1, 0, 0),
            Triple(45, 23, 10)
        ).forEach {
            val (hour, minute, seconds) = it
            val expectedString: String = if (hour > 0) {
                "%02d:%02d:%02d".format(hour, minute, seconds)
            } else {
                "%02d:%02d".format(minute, seconds)
            }

            val simulatedTask = createRecord(hour, minute, seconds)
            assertThat("$hour hour(s), $minute minute(s), and $seconds second(s) formatted as $expectedString",
                RecordFormatter.durationStringForRecord(simulatedTask), `is`(expectedString))
        }
    }
}
