package org.pointyware.timer.shared

import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.Folder
import androidx.compose.material.icons.twotone.PieChart
import androidx.compose.material.icons.twotone.Timer
import androidx.compose.ui.graphics.vector.ImageVector
import org.pointyware.timer.ui.theme.TimerIcons

enum class Screen(val image: ImageVector) {
    Timer(TimerIcons.Timer),
    Organizer(TimerIcons.Folder),
    Metrics(TimerIcons.PieChart),
    Calendar(TimerIcons.DateRange)
}
