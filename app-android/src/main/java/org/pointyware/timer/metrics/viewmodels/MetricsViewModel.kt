package org.pointyware.timer.metrics.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.timer.metrics.entities.CategoryMetric
import org.pointyware.timer.metrics.entities.Metric
import org.pointyware.timer.metrics.entities.TaskMetric
import javax.inject.Inject

@HiltViewModel
class MetricsViewModel @Inject constructor(

): ViewModel(), IMetricsViewModel {

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
