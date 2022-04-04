package com.taushsampley.timer.metrics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

/**
 */
@Composable
fun MetricsWheel(
    metricsList: List<Metric>,
    colorMap: ColorMap<Int> = RandomColorMap(),
    edgeColor: Color = Color.White,
    edgeStyle: DrawStyle = Stroke(2.0f)
) {
    val totalDuration = metricsList.sumOf { it.duration }
    val maxHeight = metricsList.maxOfOrNull { it.height } ?: 0

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1.0f)) {
        // defines draw radius of wheel
        val totalRadius = size.minDimension / 2
        val center = size.center
        // defines radius/width of each layer
        val tierRadius = totalRadius / maxHeight
        val fullBounds = Rect(center, totalRadius)

        fun drawSegments(duration: Long, segments: List<Metric>, tier: Int,
                         startAngle: Float = 0f, endAngle: Float = 360f
        ) {
            val tierInnerRadius = tier * tierRadius
            val tierOuterRadius = tierInnerRadius + tierRadius
            val tierInnerBounds = Rect(center, tierInnerRadius)
            val tierOuterBounds = Rect(center, tierOuterRadius)
            val sweep = endAngle - startAngle

            // incremented by each drawn segment
            var segmentStart = startAngle

            // for each segment, draw at this tier, then draw children if any
            segments.forEach { metric ->
                val segmentDuration = metric.duration
                val segmentSweep = sweep * segmentDuration / duration
                val segmentEnd = segmentStart + segmentSweep
                val segmentColor = colorMap[metric.id]

                val children = metric.children
                if (children.isEmpty()) {
                    // draw to final height
                    drawSegment(tierInnerBounds, fullBounds,
                        segmentStart, segmentSweep, segmentEnd,
                        segmentColor, edgeColor, edgeStyle
                    )
                } else {
                    // draw only this tier
                    drawSegment(tierInnerBounds, tierOuterBounds,
                        segmentStart, segmentSweep, segmentEnd,
                        segmentColor, edgeColor, edgeStyle
                    )

                    // draw child segments
                    drawSegments(segmentDuration, children, tier + 1, segmentStart, segmentEnd)
                }

                // increment for next segment
                segmentStart = segmentEnd
            }

        }

        drawSegments(totalDuration, metricsList, 0)
    }
}

private fun DrawScope.drawSegment(
    innerBounds: Rect,
    outerBounds: Rect,
    segmentStart: Float,
    segmentSweep: Float,
    segmentEnd: Float = segmentStart + segmentSweep,
    segmentColor: Color,
    edgeColor: Color,
    edgeStyle: DrawStyle
) {
    val segmentArc = Path()
    if (innerBounds.isEmpty) {
        val center = innerBounds.center
        segmentArc.moveTo(center.x, center.y)
    } else {
        segmentArc.addArc(innerBounds, segmentStart, segmentSweep)
    }
    segmentArc.arcTo(outerBounds, segmentEnd, -segmentSweep, false)
    segmentArc.close()
    drawPath(segmentArc, segmentColor)
    drawPath(segmentArc, edgeColor, style = edgeStyle)
}


@Preview
@Composable
fun MetricsWheelPreview() {

    val duration = 2500L
    val dummyData = listOf(
        TaskMetric(duration),
        TaskMetric(duration),
        TaskMetric(duration),
        TaskMetric(duration),
        CategoryMetric(
            TaskMetric(duration),
            TaskMetric(duration),
            CategoryMetric(
                TaskMetric(duration)
            )
        )
    )
    MetricsWheel(metricsList = dummyData)
}
