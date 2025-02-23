package org.pointyware.timer.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import org.pointyware.timer.calendar.viewmodels.CalendarViewModel
import org.pointyware.timer.metrics.viewmodels.MetricsViewModel
import org.pointyware.timer.organizer.viewmodels.OrganizerViewModel
import org.pointyware.timer.shared.TimerApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val organizerViewModel: OrganizerViewModel = OrganizerViewModel()
    private val calendarViewModel: CalendarViewModel by viewModels()
    private val metricsViewModel: MetricsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerApp(organizerViewModel, calendarViewModel, metricsViewModel)
        }
    }
}

@Preview
@Composable
fun NavigationPreview() {
    val organizerViewModel = OrganizerViewModel()
    val calendarViewModel: CalendarViewModel = viewModel()
    val metricsViewModel: MetricsViewModel = viewModel()
    TimerApp(organizerViewModel, calendarViewModel, metricsViewModel)
}
