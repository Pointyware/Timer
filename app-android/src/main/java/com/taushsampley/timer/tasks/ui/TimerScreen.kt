package com.taushsampley.timer.tasks.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.pointyware.timer.tasks.TimerScreen
import org.pointyware.timer.tasks.TimerScreenState
import org.pointyware.timer.ui.theme.TimerTheme

@Composable
fun TimerScreen(
    timerViewModel: TimerViewModel = viewModel()
) {
    val currentTime by timerViewModel.time.collectAsState()
    val isRunning by timerViewModel.isRunning.collectAsState()
    val recordingList by timerViewModel.recordings.collectAsState()
    val title by timerViewModel.taskTitle.collectAsState()
    val taskList by timerViewModel.taskList.collectAsState()
    val selectedTask by timerViewModel.selectedTask.collectAsState()
    TimerScreen(
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

@Preview(showBackground = true, name = "Timer Light")
@Preview(showBackground = true, name = "Timer Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TimerScreenPreview() {

    TimerTheme {
        TimerScreen()
    }
}
