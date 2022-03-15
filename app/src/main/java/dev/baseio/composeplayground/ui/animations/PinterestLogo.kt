package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PinterestLogoProgressAnim() {
    var rotationState by remember { mutableStateOf(0f) }
    val rotation by animateFloatAsState(
        targetValue = rotationState,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    LaunchedEffect(Unit) {
        rotationState += 360f
    }
    Canvas(modifier = Modifier
        .fillMaxSize()
        .aspectRatio(1f)
        .rotate(rotation)) {
        drawCircle(
            color = Color.Gray,
            radius = size.width.div(4)
        )
        drawCircle(
            color = Color.Black,
            radius = size.width.div(20),
            center = Offset(x = size.width.div(2), y = size.height.div(2.8f))
        )
        drawCircle(
            color = Color.Black,
            radius = size.width.div(20),
            center = Offset(x = size.width.div(2.8f), y = size.height.div(2f))
        )
        drawCircle(
            color = Color.Black,
            radius = size.width.div(20),
            center = Offset(x = size.width.div(1.55f), y = size.height.div(2f))
        )
        drawCircle(
            color = Color.Black,
            radius = size.width.div(20),
            center = Offset(x = size.width.div(2), y = size.height.div(1.55f))
        )
    }
}
