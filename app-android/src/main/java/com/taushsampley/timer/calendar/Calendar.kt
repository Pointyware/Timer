package com.taushsampley.timer.calendar

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taushsampley.timer.metrics.CategoryMetric
import com.taushsampley.timer.metrics.MetricsWheel
import com.taushsampley.timer.metrics.TaskMetric
import com.taushsampley.timer.ui.theme.TimerIcons
import com.taushsampley.timer.ui.theme.TimerTheme
import org.pointyware.timer.R

@Composable
fun MonthHeading(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    onClickHeading: () -> Unit = {},
    onClickPrevious: (() -> Unit)? = null,
    onClickNext: (() -> Unit)? = null
) {

    Column(modifier = modifier) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onClickPrevious ?: {}, enabled = onClickPrevious != null) {
                Icon(
                    TimerIcons.ArrowBack,
                    contentDescription = stringResource(R.string.acc_previous_month)
                )
            }
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .clickable(
                        onClick = onClickHeading,
                        onClickLabel = stringResource(R.string.acc_select_month)
                    )
                    .weight(1f),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = onClickNext ?: {}, enabled = onClickNext != null) {
                Icon(
                    TimerIcons.ArrowForward,
                    contentDescription = stringResource(R.string.acc_next_month)
                )
            }
        }

        Row {
            stringArrayResource(id = R.array.weekDaysShort).forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

/**
 *
 */
@Composable
fun Calendar(
    metrics: MonthMetrics?,
    modifier: Modifier = Modifier
) {

    Surface {
        Column(modifier = modifier) {
            MonthHeading(title = metrics?.month ?: R.string.loading)
            metrics?.also {
                it.metrics.forEach { week ->

                    Row {
                        week.forEach { day ->
                            CalendarDay(metrics = day, Modifier.weight(1f))
                        }
                    }
                }
            } ?: run {
                Text("Loading")
            }
        }
    }
}

@Composable
fun CalendarDay(
    metrics: DayMetrics,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {

        metrics.metrics?.let {
            MetricsWheel(metricsList = it, modifier = Modifier.padding(8.dp))
        }
        metrics.day?.let {
            Text(text = "$it")
        }
    }
}

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
