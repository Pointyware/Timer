package com.taushsampley.timer

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taushsampley.timer.ui.theme.TimerTheme

/**
 *
 */
@Composable
fun Timer(
    elapsedTime: Int,
    modifier: Modifier = Modifier,
    secondsColor: Color = MaterialTheme.colors.secondary,
    minutesColor: Color = MaterialTheme.colors.secondaryVariant,
    textStyle: TextStyle = LocalTextStyle.current,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {


        val seconds = elapsedTime % 60
        val elapsedMinutes = elapsedTime / 60
        val minutes = elapsedMinutes % 60
        val hours = elapsedMinutes / 60

        var secondsAngle = seconds * 360f / 60
        var minutesAngle = minutes * 360f / 60

        if (minutes % 2 == 1) secondsAngle -= 360f
        if (hours % 2 == 1) minutesAngle -= 360f

        Canvas(modifier = Modifier.aspectRatio(1f)) {

            drawArc(
                color = secondsColor,
                startAngle = -90f,
                sweepAngle = secondsAngle,
                useCenter = false,
                style = Stroke(width = 8f, cap = StrokeCap.Round)
            )

            drawArc(
                color = minutesColor,
                startAngle = -90f,
                sweepAngle = minutesAngle,
                topLeft = this.size.center * .05f,
                size = this.size * .95f,
                useCenter = false,
                style = Stroke(width = 8f, cap = StrokeCap.Round)
            )
        }

        Text(
            text = "%02d:%02d:%02d".format(hours, minutes, seconds),
            style = textStyle,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TimerPreview() {
    var time by remember { mutableStateOf(0) }

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
