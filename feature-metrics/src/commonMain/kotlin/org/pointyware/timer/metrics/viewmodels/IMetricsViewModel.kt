package org.pointyware.timer.metrics.viewmodels

import kotlinx.coroutines.flow.StateFlow
import org.pointyware.timer.metrics.entities.Metric

interface IMetricsViewModel {
    val metricsList: StateFlow<List<Metric>>
}

class MetricsViewModelImpl(): IMetricsViewModel {
    override val metricsList: StateFlow<List<Metric>>
        get() = TODO()
}
