package com.taushsampley.timer.metrics

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.taushsampley.timer.ui.theme.TimerTheme

/**
 */
@Composable
fun MetricsScreen(
    metricsList: List<Metric>,
    modifier: Modifier = Modifier
) {
    Column {
        MetricsWheel(metricsList)
    }
}

@Preview
@Composable
fun MetricsPreview() {
    val dummyData = listOf(
        TaskMetric(2000),
        TaskMetric(5000),
        TaskMetric(3000),
        CategoryMetric(
            TaskMetric(8000),
            TaskMetric(2000),
            CategoryMetric(
                TaskMetric(1000)
            )
        )
    )

    TimerTheme {
        MetricsScreen(dummyData)
    }
}
