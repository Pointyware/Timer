package com.taushsampley.timer.organizer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.pointyware.timer.OrganizerViewModel
import org.pointyware.timer.organizer.ui.OrganizerScreen
import org.pointyware.timer.organizer.ui.OrganizerScreenState

/**
 */
@Composable
fun OrganizerScreen(
    organizerViewModel: OrganizerViewModel
) {
    val elements by organizerViewModel.elements.collectAsState()
    OrganizerScreen(
        state = OrganizerScreenState(
            elements = elements
        ),
        onClickElement = organizerViewModel::onClickElement
    )
}
