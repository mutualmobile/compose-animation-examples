package dev.baseio.composeplayground.ui.animations.subirChakraborty

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.Subir

@Composable
fun MultiRingsLoader(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition()

    @Composable
    fun animationState(duration: Int) = infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = -360f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = duration,
                easing = LinearEasing
            )
        )
    )

    Surface(modifier.background(MaterialTheme.colors.background)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .padding(16.dp)
            ) {
                //1st ring 1st circle
                Ring(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(120.dp),
                    Color(0xFFFF6347),
                    animationState(duration = 2500)
                )
                //2nd ring 1st circle
                Ring(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(120.dp),
                    Color.Blue,
                    animationState(duration = 2100)
                )
                //1st ring 2nd circle
                Ring(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(110.dp),
                    Color.Cyan,
                    animationState(duration = 1800)
                )
                //2nd ring 2nd circle
                Ring(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(110.dp),
                    Color.Green,
                    animationState(duration = 1700)
                )
                //1st ring 3rd circle
                Ring(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(100.dp),
                    Color.Yellow,
                    animationState(duration = 1600)
                )
                //2nd ring 3rd circle
                Ring(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(100.dp),
                    Color.Magenta,
                    animationState(duration = 1200)
                )
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                Subir(Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun Ring(modifier: Modifier, ringColor: Color, positionState: State<Float>) {
    Canvas(
        modifier = modifier,
        onDraw = {
            drawArc(
                color = ringColor,
                style = Stroke(
                    width = 5f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                ),
                startAngle = positionState.value,
                sweepAngle = 360 / 4f,
                useCenter = false
            )
        })
}

@Preview
@Composable
fun MultiRingsLoaderPreview() {
    MultiRingsLoader(modifier = Modifier)
}