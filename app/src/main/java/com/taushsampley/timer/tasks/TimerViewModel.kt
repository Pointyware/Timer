package com.taushsampley.timer.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerViewModel: ViewModel() {

    private val _timer = MutableStateFlow(0)
    val time: StateFlow<Int> = _timer

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    private var timerJob: Job? = null

    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    var taskList: StateFlow<List<Task>> = _taskList
    // TODO: load initial list of tasks

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask

    private val _taskTitle = MutableStateFlow("")
    val taskTitle: StateFlow<String> = _taskTitle

    fun toggleTimer() {
        val isRunning = !_isRunning.value
        _isRunning.value = isRunning

        if (isRunning) {
            timerJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    _timer.value++
                }
            }

            val startTime = System.currentTimeMillis()
            // TODO: get task title and record start of time
        } else {
            timerJob?.cancel()
            _timer.value = 0

            val endTime = System.currentTimeMillis()
            // TODO: complete record with end time
            // TODO: if creating new task by title, add to list
        }
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
        _taskTitle.value = task.title
    }

    private var searchJob: Job? = null
    fun titleChanged(title: String) {
        _selectedTask.value = null
        _taskTitle.value = title

        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            val selectedTask = taskList.value.find {it.title == title}
            _selectedTask.value = selectedTask
        }
    }
}
