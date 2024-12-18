package org.pointyware.timer.organizer.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.timer.organizer.ui.OrganizerElement

/**
 *
 */
class OrganizerViewModel(
    // TODO: inject repository and interactors
): ViewModel(), IOrganizerViewModel {

    override fun onClickElement(organizerElement: OrganizerElement, index: Int) {
        TODO("Not yet implemented")
    }

    private val _elements = MutableStateFlow(listOf<OrganizerElement>())
    override val elements: StateFlow<List<OrganizerElement>> = _elements
}
