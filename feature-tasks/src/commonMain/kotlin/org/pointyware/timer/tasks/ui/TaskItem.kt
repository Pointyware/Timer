package org.pointyware.timer.tasks.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.pointyware.timer.entities.Task

@Composable
fun TaskItem(
    task: Task,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Text(
        text = task.title,
        modifier = Modifier
            .clickable(onClick = onSelect)
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f) // TODO: find ContentAlpha.medium
                } else {
                    MaterialTheme.colorScheme.background
                }
            )
    )
}
