package org.pointyware.timer.organizer.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.pointyware.timer.organizer.ui.OrganizerElement
import javax.inject.Inject

/**
 *
 */
@HiltViewModel
class OrganizerViewModel @Inject constructor(
    // TODO: inject repository and interactors
): ViewModel(), IOrganizerViewModel {

    override fun onClickElement(organizerElement: OrganizerElement, index: Int) {
        TODO("Not yet implemented")
    }

    private val _elements = MutableStateFlow(listOf<OrganizerElement>())
    override val elements: StateFlow<List<OrganizerElement>> = _elements
}
