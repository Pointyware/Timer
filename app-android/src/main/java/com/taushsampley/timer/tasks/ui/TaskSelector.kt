package com.taushsampley.timer.tasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.taushsampley.timer.tasks.Task
import org.pointyware.timer.R

/**
 * Allows the user to select from a list of tasks.
 * 1. Displays list of tasks
 * 2. Takes an optional task argument to indicate a previously selected task
 * 3. Indicates the newly selected task after a user taps an item
 * 4. Allows a user to cancel the interaction
 * 5. Allows the user to confirm the new task selection
 */
@Composable
fun TaskSelector(
    taskList: List<Task>,
    selectedTask: Task?,
    onSelect: (Task) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        var newTask by remember { mutableStateOf(selectedTask) }

        LazyColumn {
            items(taskList) { task ->
                TaskItem(task, isSelected = task == newTask) { newTask = task }
            }
        }
        Row {
            Button(
                onClick = onCancel,
                modifier = Modifier.semantics(mergeDescendants = true) {}
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                enabled = newTask != null,
                onClick = {
                    newTask?.also { onSelect(it) } ?: onCancel()
                },
                modifier = Modifier.semantics(mergeDescendants = true) {}
            ) {
                Text(stringResource(R.string.select))
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Text(
        text = task.title,
        modifier = Modifier
            .clickable(onClick = onSelect)
            .background(
                if (isSelected) {
                    MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.medium)
                } else {
                    MaterialTheme.colors.background
                }
            )
    )
}

@Preview
@Composable
fun TaskSelectionPreview() {
    val taskList = listOf(
        Task("alpha", 1),
        Task("beta", 2),
        Task("gamma", 3),
        Task("delta", 4)
    )
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    TaskSelector(
        taskList, selectedTask,
        onSelect = { selectedTask = it },
        {selectedTask = null}
    )
}
