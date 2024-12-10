package org.pointyware.timer.tasks.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.timer.ui.theme.TimerTheme

@Preview(showBackground = true, name = "Timer Light")
@Preview(showBackground = true, name = "Timer Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TimerScreenPreview() {

    TimerTheme {
        TimerScreen(
            TimerScreenState(
                currentTime = 0,
                isRunning = false,
                recordingList = emptyList(),
                title = "Task Title",
                taskList = emptyList(),
                selectedTask = null
            ),
            onToggleTimer = {},
            onTaskSelected = {},
            onTitleChanged = {}
        )
    }
}
