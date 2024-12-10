package org.pointyware.timer.tasks.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.pointyware.timer.tasks.TimerScreenState

@Composable
fun TimerScreen(
    timerViewModel: ITimerViewModel
) {
    val currentTime by timerViewModel.time.collectAsState()
    val isRunning by timerViewModel.isRunning.collectAsState()
    val recordingList by timerViewModel.recordings.collectAsState()
    val title by timerViewModel.taskTitle.collectAsState()
    val taskList by timerViewModel.taskList.collectAsState()
    val selectedTask by timerViewModel.selectedTask.collectAsState()
    org.pointyware.timer.tasks.TimerScreen(
        state = TimerScreenState(
            currentTime = currentTime,
            isRunning = isRunning,
            recordingList = recordingList,
            title = title,
            taskList = taskList,
            selectedTask = selectedTask
        ),
        onToggleTimer = timerViewModel::toggleTimer,
        onTitleChanged = timerViewModel::titleChanged,
        onTaskSelected = timerViewModel::selectTask
    )
}
