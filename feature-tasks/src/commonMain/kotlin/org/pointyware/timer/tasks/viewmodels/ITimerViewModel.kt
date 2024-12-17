package org.pointyware.timer.tasks.viewmodels

import kotlinx.coroutines.flow.StateFlow
import org.pointyware.timer.entities.Task
import org.pointyware.timer.tasks.ui.RecordListItem

interface ITimerViewModel {
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

class TimerViewModel(): ITimerViewModel {
    override val recordings: StateFlow<List<RecordListItem>>
        get() = TODO()

    override val time: StateFlow<Int>
        get() = TODO()

    override val isRunning: StateFlow<Boolean>
        get() = TODO()

    override val taskList: StateFlow<List<Task>>
        get() = TODO()

    override val selectedTask: StateFlow<Task?>
        get() = TODO()

    override val taskTitle: StateFlow<String>
        get() = TODO()

    override fun toggleTimer() {
        TODO()
    }

    override fun selectTask(task: Task) {
        TODO()
    }

    override fun titleChanged(title: String) {
        TODO()
    }
}
