package org.pointyware.timer.calendar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.automirrored.twotone.ArrowBack
import androidx.compose.material.icons.automirrored.twotone.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.pointyware.timer.calendar.Res
import org.pointyware.timer.calendar.acc_next_month
import org.pointyware.timer.calendar.acc_previous_month
import org.pointyware.timer.calendar.acc_select_month
import org.pointyware.timer.calendar.loading
import org.pointyware.timer.calendar.months
import org.pointyware.timer.calendar.viewmodels.DayMetrics
import org.pointyware.timer.calendar.viewmodels.MonthMetrics
import org.pointyware.timer.calendar.weekdays_short
import org.pointyware.timer.metrics.ui.MetricsWheel
import org.pointyware.timer.ui.theme.TimerIconsAutoMirrored

@Composable
fun MonthHeading(
    title: String,
    modifier: Modifier = Modifier,
    onClickHeading: () -> Unit = {},
    onClickPrevious: (() -> Unit)? = null,
    onClickNext: (() -> Unit)? = null
) {

    Column(modifier = modifier) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onClickPrevious ?: {}, enabled = onClickPrevious != null) {
                Icon(
                    TimerIconsAutoMirrored.ArrowBack,
                    contentDescription = stringResource(Res.string.acc_previous_month)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .clickable(
                        onClick = onClickHeading,
                        onClickLabel = stringResource(Res.string.acc_select_month)
                    )
                    .weight(1f),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = onClickNext ?: {}, enabled = onClickNext != null) {
                Icon(
                    TimerIconsAutoMirrored.ArrowForward,
                    contentDescription = stringResource(Res.string.acc_next_month)
                )
            }
        }

        Row {
            stringArrayResource(Res.array.weekdays_short).forEach {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun MonthTitle(
    index: Int
): String {
    return stringArrayResource(Res.array.months)[index]
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
            val title = metrics?.let { MonthTitle(index = it.month) }
                ?: stringResource(Res.string.loading)
            MonthHeading(title = title)
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
