package org.pointyware.timer.organizer.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
