package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Baap() {
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val offsetXX = remember { Animatable(0f) }
    val offsetYY= remember { Animatable(0f) }
    val rotateHalfPortion = remember { Animatable(0f) }
    val rotateFullPortion = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        runAnimation(
            coroutineScope = this,
            offsetX = offsetX,
            offsetY = offsetY,
            offsetXX = offsetXX,
            offsetYY = offsetYY,
            rotateHalfPortion = rotateHalfPortion,
            rotateFullPortion = rotateFullPortion
        )
    }

    Box(
        modifier = Modifier
            .size(width = 400.dp, height = 400.dp)
            .border(width = 2.dp, color = Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        BeesNBombs(offsetX, offsetY, offsetXX, offsetYY, rotateHalfPortion, rotateFullPortion, Modifier)
        BeesNBombs(
            offsetX, offsetY, offsetXX, offsetYY, rotateHalfPortion, rotateFullPortion,
            Modifier
                .offset(x = 34.dp, y = 14.dp)
                .rotate(45f)
        )
        BeesNBombs(
            offsetX, offsetY, offsetXX, offsetYY, rotateHalfPortion, rotateFullPortion,
            Modifier
                .offset(x = 48.dp, y = 48.dp)
                .rotate(90f)
        )
        BeesNBombs(
            offsetX, offsetY, offsetXX, offsetYY, rotateHalfPortion, rotateFullPortion,
            Modifier
                .offset(x = 34.dp, y = 82.dp)
                .rotate(135f)
        )
        BeesNBombs(
            offsetX, offsetY, offsetXX, offsetYY, rotateHalfPortion, rotateFullPortion,
            Modifier
                .offset(x = 0.dp, y = 96.dp)
                .rotate(180f)
        )
        BeesNBombs(
            offsetX, offsetY, offsetXX, offsetYY, rotateHalfPortion, rotateFullPortion,
            Modifier
                .offset(x = (-34).dp, y = 82.dp)
                .rotate(225f)
        )
        BeesNBombs(
            offsetX, offsetY, offsetXX, offsetYY, rotateHalfPortion, rotateFullPortion,
            Modifier
                .offset(x = (-48).dp, y = 48.dp)
                .rotate(270f)
        )
        BeesNBombs(
            offsetX, offsetY, offsetXX, offsetYY, rotateHalfPortion, rotateFullPortion,
            Modifier
                .offset(x = (-34).dp, y = 14.dp)
                .rotate(315f)
        )
    }
}

@Composable
fun BeesNBombs(
    offsetX: Animatable<Float, AnimationVector1D>,
    offsetY: Animatable<Float, AnimationVector1D>,
    offsetXX: Animatable<Float, AnimationVector1D>,
    offsetYY: Animatable<Float, AnimationVector1D>,
    rotateHalf: Animatable<Float, AnimationVector1D>,
    rotateFull: Animatable<Float, AnimationVector1D>,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .size(width = 40.dp, height = 40.dp)
            .offset{IntOffset(x = offsetXX.value.toInt(), y = offsetYY.value.toInt())}
            .rotate(rotateFull.value),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Triangle(Modifier.rotate(180f))
        Triangle(Modifier
            .offset { IntOffset(x = offsetX.value.toInt(), y = offsetY.value.toInt()) }
            .rotate(rotateHalf.value)
        )
    }
}

@Composable
fun Triangle(
    modifier: Modifier,
    width: Dp = 40.dp,
    height: Dp = 20.dp
) {

    Canvas(
        modifier = modifier.size(width = width, height = height),
        onDraw = {
            val trianglePath = Path()
            trianglePath.moveTo(0.dp.toPx(), 0.dp.toPx())
            trianglePath.lineTo(height.toPx(), height.toPx())
            trianglePath.lineTo(width.toPx(), 0.dp.toPx())
            trianglePath.close()
            clipPath(
                path = trianglePath,
                clipOp = ClipOp.Intersect
            ) {
                drawPath(
                    path = trianglePath,
                    brush = SolidColor(Color.Black)
                )
            }
        }
    )
}

fun runAnimation(
    coroutineScope: CoroutineScope,
    offsetX: Animatable<Float, AnimationVector1D>,
    offsetY: Animatable<Float, AnimationVector1D>,
    offsetXX: Animatable<Float, AnimationVector1D>,
    offsetYY: Animatable<Float, AnimationVector1D>,
    rotateHalfPortion: Animatable<Float, AnimationVector1D>,
    rotateFullPortion: Animatable<Float, AnimationVector1D>
) {
    coroutineScope.launch {
        delay(3000)

        coroutineScope.launch {
            offsetX.animateTo(
                74f,
                tween(easing = LinearEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            offsetY.animateTo(
                20f,
                tween(easing = LinearEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            rotateHalfPortion.animateTo(
                45f,
                tween(easing = LinearEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            delay(1500)
            offsetX.snapTo(0f)
            offsetY.snapTo(0f)
            rotateHalfPortion.snapTo(0f)

            rotateFullPortion.animateTo(
                -135f,
                tween(easing = FastOutSlowInEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            delay(1500)
            offsetXX.animateTo(
                100f,
                tween(easing = FastOutSlowInEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            delay(1500)
            offsetYY.animateTo(
                40f,
                tween(easing = FastOutSlowInEasing, durationMillis = 1500)
            )
        }
        offsetXX.snapTo(0f)
        offsetYY.snapTo(0f)
        rotateFullPortion.snapTo(0f)

        runAnimation(
            coroutineScope = this,
            offsetX = offsetX,
            offsetY = offsetY,
            offsetXX = offsetXX,
            offsetYY = offsetYY,
            rotateHalfPortion = rotateHalfPortion,
            rotateFullPortion = rotateFullPortion
        )
    }
}

@Preview
@Composable
fun PreviewTriangle() {
    Baap()
}