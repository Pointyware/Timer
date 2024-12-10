package org.pointyware.timer.metrics.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.pointyware.timer.metrics.viewmodels.IMetricsViewModel

/**
 */
@Composable
fun MetricsScreen(
    metricsViewModel: IMetricsViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val colorMap = remember { RandomColorMap<Int>() }
        val metricsList by metricsViewModel.metricsList.collectAsState()

        MetricsWheel(metricsList, colorMap = colorMap)
    }
}
