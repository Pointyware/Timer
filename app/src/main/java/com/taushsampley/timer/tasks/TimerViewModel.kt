package com.taushsampley.timer.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        }
    }
}
