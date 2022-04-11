package com.taushsampley.timer.tasks

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.twotone.Task
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.taushsampley.timer.R
import com.taushsampley.timer.ui.theme.TimerIcons
import com.taushsampley.timer.ui.theme.TimerTheme

@Composable
fun TimerScreen(
    timerViewModel: TimerViewModel
) {

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            val currentTime by timerViewModel.time.collectAsState()
            val isRunning by timerViewModel.isRunning.collectAsState()
            val title by timerViewModel.taskTitle.collectAsState()
            val taskList by timerViewModel.taskList.collectAsState()
            val selectedTask by timerViewModel.selectedTask.collectAsState()

            Timer(currentTime, modifier = Modifier.padding(24.dp))
            TaskList(modifier = Modifier.weight(1f))
            TaskControl(
                running = isRunning,
                onToggleTimer = timerViewModel::toggleTimer,
                title = title,
                onTitleChanged = timerViewModel::titleChanged,
                taskList = taskList,
                selectedTask = selectedTask,
                onTaskSelected = timerViewModel::selectTask,
            )
        }
    }
}


private fun defaultTaskList(): List<RecordListItem> {
    val currentTime = System.currentTimeMillis()
    return listOf(
        RecordListItem("programming", "00:00:00")
    )
}

/**
 * List of prior task times
 */
@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    recordList: List<RecordListItem> = defaultTaskList()
) {

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(recordList) { record ->

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = record.title, style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = record.duration,
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
fun TaskControl(
    running: Boolean,
    onToggleTimer: () -> Unit,
    title: String,
    onTitleChanged: (String) -> Unit,
    taskList: List<Task>,
    selectedTask: Task?,
    onTaskSelected: (Task) -> Unit
) {
    // TODO: bind and update with view model
    var text by remember { mutableStateOf("") }

    // TODO: add background to represent a surface that will cover task list
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 16.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(!running) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Task Title") }
                )
            }
            Button(
                onClick = onToggleTimer
            ) {
                Text(text = stringResource(if (running) R.string.stop_task else R.string.start_task))
            }
            // TODO: hide text field when timer is running and change button text to "Stop Task"
        }
    }
}

@Preview(showBackground = true, name = "Timer Light")
@Preview(showBackground = true, name = "Timer Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TimerScreenPreview() {

    val viewModel = TimerViewModel()

    TimerTheme {
        TimerScreen(viewModel)
    }
}
