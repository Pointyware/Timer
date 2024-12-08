package org.pointyware.timer.tasks

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle

/**
 *
 */
@Composable
fun Timer(
    elapsedTime: Int,
    modifier: Modifier = Modifier,
    secondsColor: Color = MaterialTheme.colorScheme.secondary,
    minutesColor: Color = MaterialTheme.colorScheme.secondaryContainer,
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
