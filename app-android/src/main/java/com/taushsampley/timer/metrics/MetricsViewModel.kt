package com.taushsampley.timer.metrics

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.timer.metrics.entities.CategoryMetric
import org.pointyware.timer.metrics.entities.Metric
import org.pointyware.timer.metrics.entities.TaskMetric
import org.pointyware.timer.metrics.viewmodels.IMetricsViewModel

class MetricsViewModel: ViewModel(), IMetricsViewModel {

    private val _metricsList = MutableStateFlow<List<Metric>>(listOf())
    override val metricsList: StateFlow<List<Metric>> = _metricsList

    init {
        _metricsList.value = listOf(
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
    }
}
