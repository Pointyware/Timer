package com.taushsampley.timer.metrics

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.timer.metrics.ui.MetricsScreen
import org.pointyware.timer.ui.theme.TimerTheme

@Preview
@Composable
fun MetricsPreview() {

    val viewModel = MetricsViewModel()

    TimerTheme {
        MetricsScreen(viewModel)
    }
}
