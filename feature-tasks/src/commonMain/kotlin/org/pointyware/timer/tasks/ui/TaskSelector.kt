package org.pointyware.timer.tasks.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import org.jetbrains.compose.resources.stringResource
import org.pointyware.timer.entities.Task
import org.pointyware.timer.tasks.Res
import org.pointyware.timer.tasks.cancel
import org.pointyware.timer.tasks.select

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
                Text(stringResource(Res.string.cancel))
            }
            Button(
                enabled = newTask != null,
                onClick = {
                    newTask?.also { onSelect(it) } ?: onCancel()
                },
                modifier = Modifier.semantics(mergeDescendants = true) {}
            ) {
                Text(stringResource(Res.string.select))
            }
        }
    }
}
