package org.pointyware.timer.tasks.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.pointyware.timer.ui.theme.TimerTheme

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TimerPreview() {
    var time by remember { mutableIntStateOf(0) }

    TimerTheme {
        Surface {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                // Element of Focus
                Timer(time, modifier = Modifier.fillMaxWidth(.5f))

                // Testing controls
                Row {

                    Button(
                        onClick = {
                            time += 3600
                        }
                    ) {
                        Text("Hours")
                    }

                    Button(
                        onClick = {
                            time += 60
                        }
                    ) {
                        Text("Minutes")
                    }

                    Button(
                        onClick = {
                            time += 1
                        }
                    ) {
                        Text("Seconds")
                    }
                }
                Button(
                    onClick = {
                        time = 0
                    }
                ) {
                    Text("Reset")
                }
            }
        }
    }
}
