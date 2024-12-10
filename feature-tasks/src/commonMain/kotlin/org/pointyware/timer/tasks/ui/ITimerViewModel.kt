package org.pointyware.timer.tasks.ui

import kotlinx.coroutines.flow.StateFlow
import org.pointyware.timer.entities.Task

interface ITimerViewModel { // TODO: rename after migration
    val recordings: StateFlow<List<RecordListItem>>
    val time: StateFlow<Int>
    val isRunning: StateFlow<Boolean>
    val taskList: StateFlow<List<Task>>
    val selectedTask: StateFlow<Task?>
    val taskTitle: StateFlow<String>
    fun toggleTimer()
    fun selectTask(task: Task)
    fun titleChanged(title: String)
}
