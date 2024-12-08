package com.taushsampley.timer.tasks.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.timer.entities.Task
import org.pointyware.timer.tasks.ui.TaskSelector

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
