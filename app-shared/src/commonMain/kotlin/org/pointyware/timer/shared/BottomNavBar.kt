package org.pointyware.timer.shared

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomNavBar(
    currentScreen: Screen,
    onClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {

    BottomAppBar(modifier) {
        Screen.entries.forEach {
            NavigationBarItem(
                selected = currentScreen == it,
                onClick = { onClick(it) },
                icon = {
                    Icon(it.image, contentDescription = null)
                }
            )
        }
    }
}
