package org.pointyware.timer.organizer.viewmodels

import kotlinx.coroutines.flow.StateFlow
import org.pointyware.timer.organizer.ui.OrganizerElement

interface IOrganizerViewModel {
    val elements: StateFlow<List<OrganizerElement>>
    fun onClickElement(organizerElement: OrganizerElement, index: Int)
}
