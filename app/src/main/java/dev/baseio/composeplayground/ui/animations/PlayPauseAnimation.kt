package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.Subir
import kotlinx.coroutines.launch

@Composable
fun PlayPauseAnimation(modifier: Modifier) {
    Surface(
        modifier
            .background(MaterialTheme.colors.background)
    ) {
        val coroutineScope = rememberCoroutineScope()
        // Initially the value will be 360f to make a full circle
        val animationTargetState = remember { mutableStateOf(360f) }
        var paused by remember { mutableStateOf(false) }

        val animatedFloatState = animateFloatAsState(
            targetValue = animationTargetState.value,
            animationSpec = tween(1200, easing = LinearEasing)
        )

        val sweepAngle by animateFloatAsState(
            targetValue = animationTargetState.value,
            animationSpec = tween(1500)
        )

        val playAnim by animateFloatAsState(
            targetValue = if (paused) 0f else 1f,
            animationSpec = tween(600)
        )

        val pauseAnim by animateFloatAsState(
            targetValue = if (paused) 1f else 0f,
            animationSpec = tween(800)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(180.dp)
                    .padding(24.dp)
            ) {
                Canvas(modifier = Modifier
                    .align(Alignment.Center)
                    .size(150.dp)
                    .clickable(enabled = true, onClickLabel = null, role = null, onClick = {
                        coroutineScope.launch {
                            paused = !paused
                            animationTargetState.value = if (animationTargetState.value != 360f) {
                                360f
                            } else {
                                -360f
                            }
                        }
                    }), onDraw = {
                    drawArc(
                        color =
                        Color(0xFFFF6347),
                        style = Stroke(
                            width = 15f,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round,
                        ),
                        startAngle = animatedFloatState.value,
                        sweepAngle = sweepAngle,
                        useCenter = false
                    )
                })
                Play(modifier = Modifier
                    .align(Alignment.Center)
                    .size(width = 60.dp, height = 60.dp)
                    .alpha(playAnim)
                    .graphicsLayer {
                        scaleX = playAnim
                    })
                Row(modifier = Modifier.align(Alignment.Center)) {
                    PauseRect(modifier = Modifier
                        .size(width = 20.dp, height = 60.dp)
                        .alpha(pauseAnim)
                        .graphicsLayer {
                            scaleX = pauseAnim
                        })
                    Spacer(modifier = Modifier.width(20.dp))
                    PauseRect(modifier = Modifier
                        .size(width = 20.dp, height = 60.dp)
                        .alpha(pauseAnim)
                        .graphicsLayer {
                            scaleX = pauseAnim
                        })
                }
            }
            Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                Subir(Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun Play(modifier: Modifier) {
    Canvas(modifier = modifier,
        onDraw = {
            val trianglePath = Path()
            trianglePath.moveTo(30f, 0f)
            trianglePath.lineTo(30f, size.height)
            trianglePath.lineTo(size.width, size.height / 2)
            clipPath(
                path = trianglePath,
                clipOp = ClipOp.Intersect
            ) {
                drawPath(
                    path = trianglePath,
                    brush = SolidColor(Color(0xFFFF6347))
                )
            }
        }
    )
}

@Composable
fun PauseRect(modifier: Modifier) {
    Canvas(modifier = modifier,
        onDraw = {
            val rectPath = Path()
            rectPath.moveTo(0f, 0f)
            rectPath.lineTo(0f, size.height)
            rectPath.lineTo(size.width, size.height)
            rectPath.lineTo(size.width, 0f)
            rectPath.close()
            clipPath(
                path = rectPath,
                clipOp = ClipOp.Intersect
            ) {
                drawPath(
                    path = rectPath,
                    brush = SolidColor(Color(0xFFFF6347))
                )
            }
        }
    )
}

@Preview
@Composable
fun PlayPauseAnimationPreview() {
    PlayPauseAnimation(Modifier)
}