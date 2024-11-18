package com.taushsampley.timer.organizer

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.twotone.Folder
import androidx.compose.material.icons.twotone.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.pointyware.timer.R
import org.pointyware.timer.shared.ui.theme.TimerIcons
import org.pointyware.timer.shared.ui.theme.TimerTheme

/**
 * Presents a list of hierarchical elements as described by [elements].
 *
 * @param elements The hierarchy elements to display.
 * @param onClickElement When a user clicks on an organizer element, will receive the element and
 * its index.
 */
@Composable
fun Organizer(
    elements: List<OrganizerElement>,
    onClickElement: (OrganizerElement, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    
    LazyColumn {
        items(elements) {
            OrganizerRow(it)
        }
    }
}

/**
 * Presents the information about a particular element. Depth is expressed as left-spacing, an icon
 * is used to show if the element is a branch or leaf node, and the title is displayed as text.
 */
@Composable
fun OrganizerRow(element: OrganizerElement) {

    val typeString = if (element.type == OrganizerElement.Type.Branch)
        stringResource(R.string.acc_name_category)
    else stringResource(R.string.acc_name_task)

    Row(
        modifier = Modifier.clearAndSetSemantics {
            contentDescription = "$typeString ${element.title}"
        }
    ) {
        Spacer(modifier = Modifier.width((8*element.level).dp))
        Icon(
            imageVector = if (element.type == OrganizerElement.Type.Branch) {
                TimerIcons.Folder
            } else {
                TimerIcons.Timer
            },
            contentDescription = null
        )
        Text(text = element.title)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OrganizerPreview() {
    val elementList = listOf(
        OrganizerElement("alpha", OrganizerElement.Type.Branch),
        OrganizerElement("beta", OrganizerElement.Type.Branch, 1),
        OrganizerElement("gamma", OrganizerElement.Type.Branch, 2),
        OrganizerElement("delta", OrganizerElement.Type.Leaf, 3),
        OrganizerElement("epsilon", OrganizerElement.Type.Leaf, 3)
    )

    TimerTheme {
        Surface {
            Organizer(elements = elementList, onClickElement = { _, _ -> })
        }
    }
}
