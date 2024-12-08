package org.pointyware.timer.tasks

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
import androidx.compose.material.icons.twotone.Task
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.stringResource
import org.pointyware.timer.entities.Task
import org.pointyware.timer.tasks.ui.RecordListItem
import org.pointyware.timer.tasks.ui.TaskSelector
import org.pointyware.timer.ui.theme.TimerIcons

data class TimerScreenState(
    val currentTime: Int,
    val isRunning: Boolean,
    val recordingList: List<RecordListItem>,
    val title: String,
    val taskList: List<Task>,
    val selectedTask: Task?
)

/**
 *
 */
@Composable
fun TimerScreen(
    state: TimerScreenState,
    onToggleTimer: () -> Unit,
    onTitleChanged: (String) -> Unit,
    onTaskSelected: (Task) -> Unit
) {
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            Timer(state.currentTime, modifier = Modifier.padding(24.dp))
            RecordingList(modifier = Modifier.weight(1f), state.recordingList)
            TaskControl(
                running = state.isRunning,
                onToggleTimer = onToggleTimer,
                title = state.title,
                onTitleChanged = onTitleChanged,
                taskList = state.taskList,
                selectedTask = state.selectedTask,
                onTaskSelected = onTaskSelected,
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
                shadowElevation = 2.dp,
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = record.title, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = record.duration,
                        style = MaterialTheme.typography.bodyLarge
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

    var taskSelectionOpen by remember { mutableStateOf(false) }

    // TODO: add background to represent a surface that will cover task list
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 16.dp
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
                    label = { Text(stringResource(Res.string.label_task_title)) },
                    trailingIcon = if (taskList.isNotEmpty()) {
                        {
                            IconButton(onClick = { taskSelectionOpen = true }) {
                                Icon(
                                    imageVector = TimerIcons.Task,
                                    contentDescription = stringResource(Res.string.acc_open_task_selector)
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
                Text(text = stringResource(if (running) Res.string.stop_task else Res.string.start_task))
            }
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
