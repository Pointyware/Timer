package org.pointyware.timer.shared

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.stringResource
import org.pointyware.timer.calendar.ui.CalendarScreen
import org.pointyware.timer.calendar.viewmodels.ICalendarViewModel
import org.pointyware.timer.metrics.ui.MetricsScreen
import org.pointyware.timer.metrics.viewmodels.IMetricsViewModel
import org.pointyware.timer.organizer.ui.OrganizerScreen
import org.pointyware.timer.organizer.viewmodels.IOrganizerViewModel
import org.pointyware.timer.tasks.ui.TimerScreen
import org.pointyware.timer.tasks.viewmodels.ITimerViewModel
import org.pointyware.timer.ui.theme.TimerIconsAutoMirrored
import org.pointyware.timer.ui.theme.TimerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerApp(
    timerViewModel: ITimerViewModel,
    organizerViewModel: IOrganizerViewModel,
    calendarViewModel: ICalendarViewModel,
    metricsViewModel: IMetricsViewModel
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
                                stringResource(Res.string.acc_navigate_back)
                            )
                        }
                    },
                    title = {
                        Text(text = stringResource(Res.string.app_name))
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
