package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private const val AnimDuration = 1000
private const val CheckAnimDuration = AnimDuration.div(2)
private val CheckAnimationSpec = tween<Float>(durationMillis = CheckAnimDuration)
private val LoaderColor = Color(0xff0C83FC)
private const val LineWidth = 8f
private const val StartSweepState = 25f
private const val Line1StartOffsetX = 0.3f
private const val Line1StartOffsetY = 0.53f
private const val Line2StartOffsetX = 0.45f
private const val Line2StartOffsetY = 0.7f
private const val Line1EndOffsetX = 0.45f
private const val Line1EndOffsetY = 0.7f
private const val Line2EndOffsetX = 0.7f
private const val Line2EndOffsetY = 0.3f

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun SyncingLoader() {
    val transition = rememberInfiniteTransition()
    val startAngle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = AnimDuration, easing = LinearEasing)
        )
    )
    var sweepState by remember { mutableStateOf(StartSweepState) }
    val sweepAngle by animateFloatAsState(
        targetValue = sweepState,
        animationSpec = tween(durationMillis = AnimDuration, easing = LinearOutSlowInEasing)
    )

    var line1OffsetXState by remember { mutableStateOf(Line1StartOffsetX) }
    var line1OffsetYState by remember { mutableStateOf(Line1StartOffsetY) }
    var line2OffsetXState by remember { mutableStateOf(Line2StartOffsetX) }
    var line2OffsetYState by remember { mutableStateOf(Line2StartOffsetY) }

    val line1OffsetX by animateFloatAsState(
        targetValue = line1OffsetXState,
        animationSpec = CheckAnimationSpec
    )
    val line1OffsetY by animateFloatAsState(
        targetValue = line1OffsetYState,
        animationSpec = CheckAnimationSpec
    )
    val line2OffsetX by animateFloatAsState(
        targetValue = line2OffsetXState,
        animationSpec = CheckAnimationSpec
    )
    val line2OffsetY by animateFloatAsState(
        targetValue = line2OffsetYState,
        animationSpec = CheckAnimationSpec
    )

    LaunchedEffect(sweepState) {
        if (sweepState == StartSweepState) {
            delay(3000)
            line1OffsetXState = Line1EndOffsetX
            line1OffsetYState = Line1EndOffsetY
            delay(CheckAnimDuration.toLong())
            line2OffsetXState = Line2EndOffsetX
            line2OffsetYState = Line2EndOffsetY
            sweepState = 360f
        }
    }

    LaunchedEffect(sweepState) {
        if (sweepState == 360f) {
            delay(3000)
            sweepState = StartSweepState
            line1OffsetXState = Line1StartOffsetX
            line1OffsetYState = Line1StartOffsetY
            line2OffsetXState = Line2StartOffsetX
            line2OffsetYState = Line2StartOffsetY
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Canvas(
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .aspectRatio(1f)
        ) {
            drawArc(
                color = LoaderColor,
                startAngle = if (sweepAngle != 360f) startAngle else 0f,
                sweepAngle = if (sweepAngle != 360f) sweepAngle else 360f,
                useCenter = false,
                style = Stroke(
                    width = LineWidth,
                    cap = StrokeCap.Round
                )
            )
            if (line1OffsetXState != Line1StartOffsetX) {
                drawLine(
                    color = LoaderColor,
                    start = Offset(
                        size.width.times(Line1StartOffsetX),
                        size.height.times(Line1StartOffsetY)
                    ),
                    end = Offset(
                        size.width.times(line1OffsetX),
                        size.height.times(line1OffsetY)
                    ),
                    strokeWidth = LineWidth,
                    cap = StrokeCap.Round
                )
            }
            if (line2OffsetXState != Line2StartOffsetX) {
                drawLine(
                    color = LoaderColor,
                    start = Offset(
                        size.width.times(Line2StartOffsetX),
                        size.height.times(Line2StartOffsetY)
                    ),
                    end = Offset(
                        size.width.times(line2OffsetX),
                        size.height.times(line2OffsetY)
                    ),
                    strokeWidth = 8f,
                    cap = StrokeCap.Round
                )
            }
        }
        AnimatedContent(
            targetState = line1OffsetXState == Line1EndOffsetX,
            transitionSpec = {
                slideInVertically { it / 2 } with slideOutVertically { -it }
            }
        ) { isLoaded ->
            if (isLoaded) {
                Text(
                    "Synced",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 4.dp)
                )
            } else {
                Text(
                    "Synced", style = MaterialTheme.typography.h6, modifier = Modifier
                        .padding(start = 4.dp)
                        .alpha(0f)
                )
            }
        }
    }
}