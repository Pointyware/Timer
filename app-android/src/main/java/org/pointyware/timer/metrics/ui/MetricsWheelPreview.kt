package org.pointyware.timer.metrics.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.timer.metrics.entities.CategoryMetric
import org.pointyware.timer.metrics.entities.TaskMetric


@Preview
@Composable
fun MetricsWheelPreview() {

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
    MetricsWheel(metricsList = dummyData)
}
