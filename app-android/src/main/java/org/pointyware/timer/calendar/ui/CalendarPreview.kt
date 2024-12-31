package org.pointyware.timer.calendar.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.timer.android.R
import org.pointyware.timer.calendar.viewmodels.DayMetrics
import org.pointyware.timer.calendar.viewmodels.MonthMetrics
import org.pointyware.timer.metrics.entities.CategoryMetric
import org.pointyware.timer.metrics.entities.TaskMetric
import org.pointyware.timer.ui.theme.TimerTheme

@Preview
@Composable
fun CalendarDayPreview() {

    val duration = 2500L
    val dummyData = listOf(
        TaskMetric(duration),
        TaskMetric(duration),
        TaskMetric(duration),
        TaskMetric(duration),
        CategoryMetric(
            TaskMetric(duration),
            TaskMetric(duration),
            CategoryMetric(
                TaskMetric(duration)
            )
        )
    )

    val blankDay = DayMetrics(null, null)
    val blankMetrics = DayMetrics(1, null)
    val metrics = DayMetrics(3, dummyData)

    TimerTheme {
        Row {
            CalendarDay(blankDay, Modifier.weight(1f))
            CalendarDay(blankMetrics, Modifier.weight(1f))
            CalendarDay(metrics, Modifier.weight(1f))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CalendarPreview() {

    fun dayMetrics(day: Int): DayMetrics
    = if ((1..31).contains(day)) {
        val data = listOf(
            TaskMetric(5000),
            TaskMetric(3000)
        )
        DayMetrics(day, data)
    } else {
        DayMetrics(null, null)
    }

    fun weekMetrics(startDay: Int)
    = (0 until 7).map {
        dayMetrics(startDay + it)
    }

    fun monthMetrics(weekCount: Int, offset: Int = 0)
    = (0 until weekCount).map {
        weekMetrics(it*7 + offset)
    }

    val metrics = remember {
        MonthMetrics(
            R.string.month_may, 2022, monthMetrics(6, -4)
        )
    }

    TimerTheme {
        Calendar(metrics)
    }
}
