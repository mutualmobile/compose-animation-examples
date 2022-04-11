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

@Composable
fun AnimatedLoader() {
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val rotateTopTriangle = remember { Animatable(-33f) }
    val rotateBottomTriangle = remember { Animatable(200f) }

    LaunchedEffect(key1 = true) {
        runAnimation(
            coroutineScope = this,
            offsetX = offsetX,
            offsetY = offsetY,
            rotateTopTriangle = rotateTopTriangle,
            rotateBottomTriangle = rotateBottomTriangle
        )
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(width = 400.dp, height = 400.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedSquare(offsetX, offsetY, rotateBottomTriangle, rotateTopTriangle, Modifier)
            AnimatedSquare(
                offsetX, offsetY, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = 34.dp, y = 14.dp)
                    .rotate(45f)
            )
            AnimatedSquare(
                offsetX, offsetY, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = 48.dp, y = 48.dp)
                    .rotate(90f)
            )
            AnimatedSquare(
                offsetX, offsetY, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = 34.dp, y = 82.dp)
                    .rotate(135f)
            )
            AnimatedSquare(
                offsetX, offsetY, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = 0.dp, y = 96.dp)
                    .rotate(180f)
            )
            AnimatedSquare(
                offsetX, offsetY, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = (-34).dp, y = 82.dp)
                    .rotate(225f)
            )
            AnimatedSquare(
                offsetX, offsetY, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = (-48).dp, y = 48.dp)
                    .rotate(270f)
            )
            AnimatedSquare(
                offsetX, offsetY, rotateBottomTriangle, rotateTopTriangle,
                Modifier
                    .offset(x = (-34).dp, y = 14.dp)
                    .rotate(315f)
            )
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)) {
            Subir(Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun AnimatedSquare(
    offsetX: Animatable<Float, AnimationVector1D>,
    offsetY: Animatable<Float, AnimationVector1D>,
    rotateBottomTriangle: Animatable<Float, AnimationVector1D>,
    rotateTopTriangle: Animatable<Float, AnimationVector1D>,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .size(width = 40.dp, height = 40.dp)
            .offset { IntOffset(x = offsetX.value.toInt(), y = offsetY.value.toInt()) },
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
            trianglePath.lineTo(x = size.width / 2, y = size.width / 2) //
            trianglePath.lineTo(x = width.toPx(), y = 0.dp.toPx())
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
    offsetY: Animatable<Float, AnimationVector1D>,
    rotateTopTriangle: Animatable<Float, AnimationVector1D>,
    rotateBottomTriangle: Animatable<Float, AnimationVector1D>
) {
    coroutineScope.launch {
        delay(6000)

        coroutineScope.launch {
            offsetX.animateTo(
                -200f,
                tween(easing = FastOutSlowInEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            delay(1500)
            rotateTopTriangle.animateTo(
                33f,
                tween(easing = FastOutSlowInEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            delay(1500)
            rotateBottomTriangle.animateTo(
                160f,
                tween(easing = FastOutSlowInEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            delay(3000)
            offsetX.animateTo(
                0f,
                tween(easing = FastOutSlowInEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            delay(4500)
            rotateTopTriangle.animateTo(
                -28f,
                tween(easing = FastOutSlowInEasing, durationMillis = 1500)
            )
        }
        coroutineScope.launch {
            delay(4500)
            rotateBottomTriangle.animateTo(
                208f,
                tween(easing = FastOutSlowInEasing, durationMillis = 1500)
            )
        }

        runAnimation(
            coroutineScope = this,
            offsetX = offsetX,
            offsetY = offsetY,
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