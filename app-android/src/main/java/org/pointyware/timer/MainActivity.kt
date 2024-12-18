package org.pointyware.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.automirrored.twotone.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.pointyware.timer.calendar.ui.CalendarScreen
import org.pointyware.timer.calendar.viewmodels.CalendarViewModel
import org.pointyware.timer.metrics.ui.MetricsScreen
import org.pointyware.timer.metrics.viewmodels.MetricsViewModel
import org.pointyware.timer.organizer.ui.OrganizerScreen
import org.pointyware.timer.organizer.viewmodels.OrganizerViewModel
import org.pointyware.timer.shared.BottomNavBar
import org.pointyware.timer.shared.Screen
import org.pointyware.timer.tasks.ui.TimerScreen
import org.pointyware.timer.tasks.ui.TimerViewModel
import org.pointyware.timer.ui.theme.TimerIconsAutoMirrored
import org.pointyware.timer.ui.theme.TimerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val timerViewModel: TimerViewModel by viewModels()
    private val organizerViewModel: OrganizerViewModel = OrganizerViewModel()
    private val calendarViewModel: CalendarViewModel by viewModels()
    private val metricsViewModel: MetricsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerApp(timerViewModel, organizerViewModel, calendarViewModel, metricsViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerApp(
    timerViewModel: TimerViewModel,
    organizerViewModel: OrganizerViewModel,
    calendarViewModel: CalendarViewModel,
    metricsViewModel: MetricsViewModel
) {

    TimerTheme(
        isDynamicTheme = true // no-op on non-Android platforms
    ) {

        var currentScreen by rememberSaveable { mutableStateOf(Screen.Timer) }
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                TimerIconsAutoMirrored.ArrowBack,
                                stringResource(R.string.acc_navigate_back)
                            )
                        }
                    },
                    title = {
                        Text(text = stringResource(R.string.app_name))
                    }
                )
            },
            bottomBar = {
                BottomNavBar(
                    currentScreen = currentScreen,
                    onClick = {
                        navController.navigate(it.name)
                        currentScreen = it
                    }
                )
            }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Screen.Timer.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Timer.name) {
                    TimerScreen(timerViewModel)
                }
                composable(Screen.Organizer.name) {
                    OrganizerScreen(organizerViewModel)
                }
                composable(Screen.Metrics.name) {
                    MetricsScreen(metricsViewModel)
                }
                composable(Screen.Calendar.name) {
                    CalendarScreen(calendarViewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun NavigationPreview() {
    val timerViewModel: TimerViewModel = viewModel()
    val organizerViewModel = OrganizerViewModel()
    val calendarViewModel: CalendarViewModel = viewModel()
    val metricsViewModel: MetricsViewModel = viewModel()
    TimerApp(timerViewModel, organizerViewModel, calendarViewModel, metricsViewModel)
}
