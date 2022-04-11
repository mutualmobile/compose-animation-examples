package dev.baseio.composeplayground.ui.animations.subirChakraborty

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.Subir
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val initialTopTriangleAngle = -33f
const val initialBottomTriangleAngle = 200f
const val finalTopTriangleAngle = 33f
const val finalBottomTriangleAngle = 160f
const val secondArrowRotationAngle = 45f
const val thirdArrowRotationAngle = 90f
const val fourthArrowRotationAngle = 135f
const val fifthArrowRotationAngle = 180f
const val sixthArrowRotationAngle = 225f
const val seventhArrowRotationAngle = 270f
const val eighthArrowRotationAngle = 315f

@Composable
fun AnimatedLoader() {
    val offsetX = remember { Animatable(0f) }
    val rotateTopTriangle = remember { Animatable(initialTopTriangleAngle) }
    val rotateBottomTriangle = remember { Animatable(initialBottomTriangleAngle) }

    LaunchedEffect(key1 = true) {
        runAnimation(
            coroutineScope = this,
            offsetX = offsetX,
            rotateTopTriangle = rotateTopTriangle,
            rotateBottomTriangle = rotateBottomTriangle
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(width = 400.dp, height = 400.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedSquare(offsetX, rotateBottomTriangle, rotateTopTriangle, Modifier)
            AnimatedSquare(
                offsetX, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = 34.dp, y = 14.dp)
                    .rotate(secondArrowRotationAngle)
            )
            AnimatedSquare(
                offsetX, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = 48.dp, y = 48.dp)
                    .rotate(thirdArrowRotationAngle)
            )
            AnimatedSquare(
                offsetX, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = 34.dp, y = 82.dp)
                    .rotate(fourthArrowRotationAngle)
            )
            AnimatedSquare(
                offsetX, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = 0.dp, y = 96.dp)
                    .rotate(fifthArrowRotationAngle)
            )
            AnimatedSquare(
                offsetX, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = (-34).dp, y = 82.dp)
                    .rotate(sixthArrowRotationAngle)
            )
            AnimatedSquare(
                offsetX, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = (-48).dp, y = 48.dp)
                    .rotate(seventhArrowRotationAngle)
            )
            AnimatedSquare(
                offsetX, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = (-34).dp, y = 14.dp)
                    .rotate(eighthArrowRotationAngle)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Subir(Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun AnimatedSquare(
    offsetX: Animatable<Float, AnimationVector1D>,
    rotateBottomTriangle: Animatable<Float, AnimationVector1D>,
    rotateTopTriangle: Animatable<Float, AnimationVector1D>,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .size(width = 40.dp, height = 40.dp)
            .offset { IntOffset(x = offsetX.value.toInt(), y = 0) },
    ) {
        Triangle(
            Modifier.rotate(rotateTopTriangle.value)
        )
        Triangle(
            Modifier.rotate(rotateBottomTriangle.value)
        )
    }
}

@Composable
fun Triangle(
    modifier: Modifier,
    width: Dp = 40.dp,
    color: Color = Color.White
) {
    Canvas(
        modifier = modifier.size(width = width, height = width / 2),
        onDraw = {
            val trianglePath = Path()
            trianglePath.moveTo(x = 0.dp.toPx(), y = 0.dp.toPx()) // top left
            trianglePath.lineTo(x = size.width / 2, y = size.width / 2) // bottom center
            trianglePath.lineTo(x = width.toPx(), y = 0.dp.toPx()) // top right
            trianglePath.close()
            clipPath(
                path = trianglePath,
                clipOp = ClipOp.Intersect
            ) {
                drawPath(
                    path = trianglePath,
                    brush = SolidColor(color)
                )
            }
        }
    )
}

fun runAnimation(
    coroutineScope: CoroutineScope,
    offsetX: Animatable<Float, AnimationVector1D>,
    rotateTopTriangle: Animatable<Float, AnimationVector1D>,
    rotateBottomTriangle: Animatable<Float, AnimationVector1D>
) {
    val totalDuration = 6000L
    val perAnimDuration = 1500
    val halfDuration = 3000L
    val lastHalfDuration = 4500L

    coroutineScope.launch {
        delay(totalDuration)

        // move 200 px in x axis
        coroutineScope.launch {
            offsetX.animateTo(
                -200f,
                tween(easing = FastOutSlowInEasing, durationMillis = perAnimDuration)
            )
        }
        // wait 1.5s and change arrow direction
        coroutineScope.launch {
            delay(perAnimDuration.toLong())
            rotateTopTriangle.animateTo(
                finalTopTriangleAngle,
                tween(easing = FastOutSlowInEasing, durationMillis = perAnimDuration)
            )
        }
        coroutineScope.launch {
            delay(perAnimDuration.toLong())
            rotateBottomTriangle.animateTo(
                finalBottomTriangleAngle,
                tween(easing = FastOutSlowInEasing, durationMillis = perAnimDuration)
            )
        }
        // wait 3s and move to initial position in x axis
        coroutineScope.launch {
            delay(halfDuration)
            offsetX.animateTo(
                0f,
                tween(easing = FastOutSlowInEasing, durationMillis = perAnimDuration)
            )
        }
        // wait 4.5s and change arrow direction
        coroutineScope.launch {
            delay(lastHalfDuration)
            rotateTopTriangle.animateTo(
                initialTopTriangleAngle,
                tween(easing = FastOutSlowInEasing, durationMillis = perAnimDuration)
            )
        }
        coroutineScope.launch {
            delay(lastHalfDuration)
            rotateBottomTriangle.animateTo(
                initialBottomTriangleAngle,
                tween(easing = FastOutSlowInEasing, durationMillis = perAnimDuration)
            )
        }

        runAnimation(
            coroutineScope = this,
            offsetX = offsetX,
            rotateTopTriangle = rotateTopTriangle,
            rotateBottomTriangle = rotateBottomTriangle
        )
    }
}

@Preview
@Composable
fun Anim() {
    AnimatedLoader()
}