package com.taushsampley.timer

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.taushsampley.timer.ui.theme.TimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerTheme {

                TaskUi()
                /*
                TODO:
                  1. Current Task Timer
                  2. Task Time List (Task Title : Time)
                  3. New Task Title (Input Box)
                  4. Start/Pause/New (Button)
                 */
            }
        }
    }
}

/**
 * Elapsed time for current active task
 * TODO: mock out
 *   1. Elapsed Time Text, centered
 *   2. Backing Circle
 *   3. Seconds "Hand" border
 */
@Composable
fun TaskTimer() {

}

/**
 * List of prior task times
 * TODO: mock out
 *   1. Lazy Column
 *   2. Populate timers
 */
@Composable
fun TaskList() {

}

/**
 * New task creation input/controls
 * TODO: mock out
 *   1. Text Input
 *   2. Control button
 */
@Composable
fun TaskControl() {

}

/**
 * Full task timer UI
 */
@Composable
fun TaskUi() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TaskTimer()
            TaskList()
            TaskControl()
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    TimerTheme {
        TaskUi()
    }
}
