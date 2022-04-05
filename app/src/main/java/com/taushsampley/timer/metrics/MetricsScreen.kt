package com.taushsampley.timer.metrics

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.taushsampley.timer.ui.theme.TimerTheme

/**
 */
@Composable
fun MetricsScreen(
    metricsViewModel: MetricsViewModel,
    modifier: Modifier = Modifier
) {
    Column {
        val colorMap = remember { RandomColorMap<Int>() }
        val metricsList by metricsViewModel.metricsList.collectAsState()

        MetricsWheel(metricsList, colorMap)
    }
}

@Preview
@Composable
fun MetricsPreview() {

    val viewModel = MetricsViewModel()

    TimerTheme {
        MetricsScreen(viewModel)
    }
}
