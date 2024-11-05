package com.taushsampley.timer.tasks.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.twotone.Task
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taushsampley.timer.tasks.Task
import com.taushsampley.timer.ui.theme.TimerIcons
import com.taushsampley.timer.ui.theme.TimerTheme
import org.pointyware.timer.R

@Composable
fun TimerScreen(
    timerViewModel: TimerViewModel = viewModel()
) {

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            val currentTime by timerViewModel.time.collectAsState()
            val isRunning by timerViewModel.isRunning.collectAsState()
            val recordingList by timerViewModel.recordings.collectAsState()
            val title by timerViewModel.taskTitle.collectAsState()
            val taskList by timerViewModel.taskList.collectAsState()
            val selectedTask by timerViewModel.selectedTask.collectAsState()

            Timer(currentTime, modifier = Modifier.padding(24.dp))
            RecordingList(modifier = Modifier.weight(1f), recordingList)
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

/**
 * List of prior task times
 */
@Composable
fun RecordingList(
    modifier: Modifier = Modifier,
    recordList: List<RecordListItem>
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
@OptIn(ExperimentalComposeUiApi::class)
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

    var taskSelectionOpen by remember { mutableStateOf(false) }

    // TODO: add background to represent a surface that will cover task list
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 16.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(!running) {
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    modifier = Modifier.padding(top = 8.dp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    }),
                    value = title,
                    onValueChange = onTitleChanged,
                    label = { Text(stringResource(R.string.label_task_title)) },
                    trailingIcon = if (taskList.isNotEmpty()) {
                        {
                            IconButton(onClick = { taskSelectionOpen = true }) {
                                Icon(
                                    imageVector = TimerIcons.Task,
                                    contentDescription = stringResource(R.string.acc_open_task_selector)
                                )
                            }
                        }
                    } else null
                )
            }
            Button(
                onClick = onToggleTimer,
                enabled = title.isNotBlank()
            ) {
                Text(text = stringResource(if (running) R.string.stop_task else R.string.start_task))
            }
            // TODO: hide text field when timer is running and change button text to "Stop Task"
        }

        if (taskSelectionOpen) {
            Dialog(
                onDismissRequest = { taskSelectionOpen = false }
            ) {
                TaskSelector(
                    taskList = taskList,
                    selectedTask = selectedTask,
                    onSelect = onTaskSelected,
                    onCancel = { taskSelectionOpen = false }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Timer Light")
@Preview(showBackground = true, name = "Timer Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TimerScreenPreview() {

    TimerTheme {
        TimerScreen()
    }
}
