package com.taushsampley.timer.organizer

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.timer.organizer.Organizer
import org.pointyware.timer.organizer.OrganizerElement
import org.pointyware.timer.shared.ui.theme.TimerTheme

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
