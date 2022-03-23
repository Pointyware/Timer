package com.taushsampley.timer

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
 *   1. Elapsed Time Text, centered [x]
 *   2. Backing Circle [x]
 *   3. Seconds "Hand" border
 */
@Composable
fun TaskTimer() {
    // TODO: make circle and text smaller
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(MaterialTheme.colors.secondary, CircleShape)
            .aspectRatio(1f)
    ) {
        Text(
            text = "00:00:00",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
    }
}

/**
 * List of prior task times
 */
@Composable
fun TaskList() {
    val currentTime = System.currentTimeMillis()
    val taskList = listOf(
        Task("programming", currentTime, currentTime + 3641000),
        Task("cleaning", currentTime, currentTime + 7223000),
        Task("eating", currentTime, currentTime + 50000)
    ) // TODO: pull from actual data source/view model

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(taskList) { task ->

            Surface(
                modifier = Modifier
                    .padding(all = 8.dp),
                elevation = 2.dp,
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(all = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = task.title, style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = TaskFormatter.durationStringForTask(task),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
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
        color = MaterialTheme.colors.background
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
