package dev.baseio.composeplayground.ui.animations.pacman

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Fill
import dev.baseio.composeplayground.contributors.PushpalRoy
import dev.baseio.composeplayground.ui.animations.offGray

@Composable
fun Pacman(modifier: Modifier) {

  val infiniteTransition = rememberInfiniteTransition()
  val upperJawAnim by infiniteTransition.animateFloat(
    initialValue = 360f,
    targetValue = 300f,
    animationSpec = infiniteRepeatable(
      animation = tween(300, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    )
  )
  val lowerJawAnim by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 30f,
    animationSpec = infiniteRepeatable(
      animation = tween(300, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    )
  )
  val foodAnim1 by infiniteTransition.animateFloat(
    initialValue = 1000f,
    targetValue = 690f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    )
  )
  val foodAnim2 by infiniteTransition.animateFloat(
    initialValue = 1200f,
    targetValue = 890f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1200, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    )
  )
  Surface {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(offGray)
    ) {
      Canvas(
        modifier = modifier
          .fillMaxSize()
          .background(
            brush = Brush.linearGradient(
              colors = listOf(
                Color(0xFF0F1A22),
                Color(0xFF162631),
                Color(0xFF1B2F3D),
                Color(0xFF406C9E),
              )
            )
          )
      ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Pacman
        drawArc(
          brush = SolidColor(Color(0xFFFFD500)),
          startAngle = lowerJawAnim,
          sweepAngle = upperJawAnim,
          useCenter = true,
          topLeft = Offset(x = canvasWidth / 4, y = canvasHeight / 3),
          size = Size(size.minDimension / 2, size.minDimension / 2),
          style = Fill
        )

        // Eye
        drawCircle(
          color = Color.Black,
          radius = 20f,
          center = Offset(x = (canvasWidth / 4) + 300, y = (canvasHeight / 3) + 110)
        )

        // Food particle 1
        drawCircle(
          color = Color(0xFFFF3D00),
          radius = 25f,
          center = Offset(x = foodAnim1, y = (canvasHeight / 3) + 270)
        )

        // Food particle 2
        drawCircle(
          color = Color(0xFFFF3D00),
          radius = 25f,
          center = Offset(x = foodAnim2, y = (canvasHeight / 3) + 270)
        )
      }
      Box(
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .fillMaxWidth()
      ) {
        PushpalRoy(Modifier.align(Alignment.Center))
      }
    }
  }
}