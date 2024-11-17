package com.taushsampley.timer.tasks.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.pointyware.timer.TimerApplication
import org.pointyware.timer.entities.Record
import org.pointyware.timer.entities.Task
import org.pointyware.timer.interactors.CreateRecordUseCase
import org.pointyware.timer.interactors.CreateTaskUseCase

// TODO: add Hilt to inject dependencies
class TimerViewModel(application: Application): AndroidViewModel(application) {

    private val repository = getApplication<TimerApplication>().repository

    private val createTaskUseCase = CreateTaskUseCase(repository)
    private val createRecordUseCase = CreateRecordUseCase(repository, createTaskUseCase)

//    private val startRecordingUseCase = ToggleTimerUseCase()
//    private val stopRecordingUseCase = StopRecordingUseCase()

    private val _recordings = MutableStateFlow<List<RecordListItem>>(emptyList())
    val recordings: StateFlow<List<RecordListItem>> = _recordings

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

    private var startTime: Long = 0

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

            startTime = System.currentTimeMillis()
        } else {
            timerJob?.cancel()
            _timer.value = 0

            val endTime = System.currentTimeMillis()
            selectedTask.value?.also {
                viewModelScope.launch {
                    createRecordUseCase(
                        Record(
                            startTime = startTime,
                            endTime = endTime
                        ), it)
                }
            } ?: run {
                viewModelScope.launch {
                    createRecordUseCase(Record(startTime, endTime), taskTitle.value)
                }
            }
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
