package org.pointyware.timer.organizer.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.pointyware.timer.organizer.viewmodels.IOrganizerViewModel

data class OrganizerScreenState(
    val elements: List<OrganizerElement>,
)

/**
 *
 */
@Composable
fun OrganizerScreen(
    state: OrganizerScreenState,
    onClickElement: (OrganizerElement, Int) -> Unit,
) {
    Organizer(
        elements = state.elements,
        onClickElement = onClickElement,
        modifier = Modifier.fillMaxSize(),
    )
}

/**
 */
@Composable
fun OrganizerScreen(
    organizerViewModel: IOrganizerViewModel
) {
    val elements by organizerViewModel.elements.collectAsState()
    OrganizerScreen(
        state = OrganizerScreenState(
            elements = elements
        ),
        onClickElement = organizerViewModel::onClickElement
    )
}
