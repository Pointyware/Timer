package org.pointyware.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.Folder
import androidx.compose.material.icons.twotone.PieChart
import androidx.compose.material.icons.twotone.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taushsampley.timer.calendar.CalendarScreen
import com.taushsampley.timer.calendar.CalendarViewModel
import com.taushsampley.timer.metrics.MetricsScreen
import com.taushsampley.timer.metrics.MetricsViewModel
import com.taushsampley.timer.organizer.OrganizerScreen
import com.taushsampley.timer.organizer.OrganizerViewModel
import com.taushsampley.timer.tasks.ui.TimerScreen
import com.taushsampley.timer.tasks.ui.TimerViewModel
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

enum class Screen(val image: ImageVector) {
    Timer(TimerIcons.Timer),
    Organizer(TimerIcons.Folder),
    Metrics(TimerIcons.PieChart),
    Calendar(TimerIcons.DateRange)
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
                    Icon(it.image, contentDescription = null)
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
    val organizerViewModel: OrganizerViewModel = viewModel()
    val calendarViewModel: CalendarViewModel = viewModel()
    val metricsViewModel: MetricsViewModel = viewModel()
    TimerApp(timerViewModel, organizerViewModel, calendarViewModel, metricsViewModel)
}
