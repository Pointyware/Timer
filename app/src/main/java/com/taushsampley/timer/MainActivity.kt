package com.taushsampley.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.taushsampley.timer.calendar.CalendarScreen
import com.taushsampley.timer.metrics.MetricsScreen
import com.taushsampley.timer.organizer.OrganizerScreen
import com.taushsampley.timer.tasks.TimerScreen
import com.taushsampley.timer.ui.theme.TimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerApp()
        }
    }
}

enum class Screens {
    Timer,
    Organizer,
    Metrics,
    Calendar
}

@Composable
fun TimerApp() {

    TimerTheme {

        val allScreens = Screens.values()
        val currentScreen by rememberSaveable { mutableStateOf(Screens.Timer) }
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.app_name))
                    }
                )
            }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Screens.Timer.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screens.Timer.name) {
                    TimerScreen(0) // TODO: pass data from viewModel
                }
                composable(Screens.Organizer.name) {
                    Text("Organizer")
                }
                composable(Screens.Metrics.name) {
                    Text("Metrics")
                }
                composable(Screens.Calendar.name) {
                    Text("Calendar")
                }
            }
        }
    }
}

@Preview
@Composable
fun NavigationPreview() {
    TimerApp()
}
