package org.pointyware.timer.calendar.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.timer.calendar.viewmodels.CalendarViewModel
import org.pointyware.timer.ui.theme.TimerTheme

@Preview(showBackground = true,)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CalendarScreenPreview() {

    val viewModel = CalendarViewModel()

    TimerTheme {
        CalendarScreen(viewModel)
    }
}
