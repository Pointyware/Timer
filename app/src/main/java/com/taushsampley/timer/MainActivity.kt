package com.taushsampley.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taushsampley.timer.calendar.CalendarScreen
import com.taushsampley.timer.calendar.CalendarViewModel
import com.taushsampley.timer.metrics.MetricsScreen
import com.taushsampley.timer.metrics.MetricsViewModel
import com.taushsampley.timer.organizer.OrganizerScreen
import com.taushsampley.timer.organizer.OrganizerViewModel
import com.taushsampley.timer.tasks.TimerScreen
import com.taushsampley.timer.tasks.TimerViewModel
import com.taushsampley.timer.ui.theme.TimerIcons
import com.taushsampley.timer.ui.theme.TimerTheme

class MainActivity : ComponentActivity() {

    private val timerViewModel: TimerViewModel by viewModels()
    private val organizerViewModel: OrganizerViewModel by viewModels()
    private val calendarViewModel: CalendarViewModel by viewModels()
    private val metricsViewModel: MetricsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerApp(timerViewModel, organizerViewModel, calendarViewModel, metricsViewModel)
        }
    }
}

enum class Screen {
    Timer,
    Organizer,
    Metrics,
    Calendar
}

@Composable
fun BottomNavBar(
    currentScreen: Screen,
    onClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {

    BottomAppBar(modifier) {
        Screen.values().forEach {
            BottomNavigationItem(
                selected = currentScreen == it,
                onClick = { onClick(it) },
                icon = {
                    Icon(TimerIcons.Add, contentDescription = null)
                }
            )
        }
    }
}

@Composable
fun TimerApp(
    timerViewModel: TimerViewModel,
    organizerViewModel: OrganizerViewModel,
    calendarViewModel: CalendarViewModel,
    metricsViewModel: MetricsViewModel
) {

    TimerTheme {

        var currentScreen by rememberSaveable { mutableStateOf(Screen.Timer) }
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                TimerIcons.ArrowBack,
                                stringResource(R.string.navigate_back)
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
                    OrganizerScreen()
                }
                composable(Screen.Metrics.name) {
                    MetricsScreen(listOf())
                }
                composable(Screen.Calendar.name) {
                    CalendarScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun NavigationPreview() {
    val timerViewModel = TimerViewModel()
    val organizerViewModel = OrganizerViewModel()
    val calendarViewModel = CalendarViewModel()
    val metricsViewModel = MetricsViewModel()
    TimerApp(timerViewModel, organizerViewModel, calendarViewModel, metricsViewModel)
}
