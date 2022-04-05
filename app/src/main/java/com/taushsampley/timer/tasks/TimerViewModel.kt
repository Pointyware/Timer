package com.taushsampley.timer.tasks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimerViewModel: ViewModel() {

    private val _timer = MutableStateFlow(0)
    val time: StateFlow<Int> = _timer

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    fun toggleTimer() {
        _isRunning.value = !_isRunning.value
    }
}
