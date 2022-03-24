package com.taushsampley.timer

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
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
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {

        val arcColor = MaterialTheme.colors.primary
        Canvas(modifier = Modifier.aspectRatio(1f)) {

            drawArc(
                color = arcColor,
                startAngle = -90f,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(width = 8f, cap = StrokeCap.Round)
            )
        }

        Text(
            text = "00:00:00",
//            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

private fun defaultTaskList(): List<Task> {
    val currentTime = System.currentTimeMillis()
    return listOf(
        Task("programming", currentTime, currentTime + 3641000),
        Task("cleaning", currentTime, currentTime + 7223000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000),
        Task("eating", currentTime, currentTime + 50000)
    )
}

/**
 * List of prior task times
 */
@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    taskList: List<Task> = defaultTaskList()
) {

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(taskList) { task ->

            /*
             TODO:
              1. make each task item expandable and animated
              2. when expanded, show date and start/end times
              3. If same day, show date once with both times; if spans multiple days, show
                dates for each time
             */
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
 */
@Composable
fun TaskControl() {
    // TODO: bind and update with view model
    var text by remember { mutableStateOf("") }
    var running by remember { mutableStateOf(false) }

    val height by animateDpAsState(if (running) 0.dp else Float.NaN.dp)

    // TODO: add background to represent a surface that will cover task list
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary),
        elevation = 16.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Task Title") },
                modifier = Modifier
                    .height(height)
                    .animateContentSize()
            )
            Button(
                // TODO: notify view model of creation
                onClick = {
                    println("Start timing new task")
                    running = !running
                }
            ) {
                Text(text = stringResource(if (running) R.string.stop_task else R.string.start_task))
            }
            // TODO: hide text field when timer is running and change button text to "Stop Task"
        }
    }
}

/**
 * Full task timer UI
 */
@Composable
fun TaskUi() {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            TaskTimer()
            TaskList(modifier = Modifier.weight(1f))
            /*
             TODO:
               1. attach input/controls to bottom of page
               2. create space between list and controls
             */
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
