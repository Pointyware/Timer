package org.pointyware.timer.organizer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.pointyware.timer.OrganizerViewModel

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
