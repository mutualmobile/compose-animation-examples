package dev.baseio.composeplayground.ui.animations.adityabhawsar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import dev.baseio.composeplayground.contributors.AdityaBhawsar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BreatheRotatingAnimation(modifier: Modifier = Modifier) {

  val rotationIndicator = remember { Animatable(0f) }
  val dialScale = remember { Animatable(1f) }

  LaunchedEffect(key1 = true) {
    runDialAnimation(
      this,
      rotationIndicator,
      dialScale
    )
  }

  Box(
    modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {

    Canvas(
      modifier = Modifier
        .size(250.dp)
        .scale(dialScale.value)
        .align(Alignment.Center)
    ) {

      drawCircle(
        color = Color.LightGray
      )
      drawArc(
        color = Color.Blue,
        useCenter = true,
        startAngle = 270f,
        sweepAngle = 90f
      )
      drawArc(
        color = Color.Cyan,
        useCenter = true,
        startAngle = 90f,
        sweepAngle = 90f
      )
      drawCircle(
        color = Color.Black,
        radius = (size.maxDimension / 2.0f) - 10f
      )
      rotate(
        45f
      ) {
        drawCircle(
          brush = Brush.verticalGradient(
            listOf(
              Color.Blue,
              Color.Cyan
            )
          ),
          radius = (size.maxDimension / 2.0f) - 40f
        )
      }

      drawArc(
        color = Color.White,
        startAngle = 180f,
        sweepAngle = 180f,
        useCenter = true,
        topLeft = Offset(x = this.size.width.times(0.47f), y = this.size.height.times(.96f)),
        size = Size(width = 50f, height = 52f)
      )

      rotate(90f) {
        drawArc(
          color = Color.White,
          startAngle = 180f,
          sweepAngle = 180f,
          useCenter = true,
          topLeft = Offset(x = this.size.width.times(0.47f), y = this.size.height.times(.96f)),
          size = Size(width = 50f, height = 52f)
        )
      }

      rotate(180f) {
        drawArc(
          color = Color.White,
          startAngle = 180f,
          sweepAngle = 180f,
          useCenter = true,
          topLeft = Offset(x = this.size.width.times(0.47f), y = this.size.height.times(.96f)),
          size = Size(width = 50f, height = 52f)
        )
      }

      rotate(270f) {
        drawArc(
          color = Color.White,
          startAngle = 180f,
          sweepAngle = 180f,
          useCenter = true,
          topLeft = Offset(x = this.size.width.times(0.47f), y = this.size.height.times(.96f)),
          size = Size(width = 50f, height = 52f)
        )
      }

      rotate(rotationIndicator.value) {
        drawArc(
          color = Color.Blue,
          startAngle = 180f,
          sweepAngle = 180f,
          useCenter = true,
          topLeft = Offset(x = this.size.width.times(0.47f), y = this.size.height.times(.96f)),
          size = Size(width = 50f, height = 52f)
        )
      }

    }

    Box(modifier = Modifier.align(Alignment.Center)){

      AnimatedVisibility(
        visible = (
            (rotationIndicator.value >= 90f && rotationIndicator.value < 180f)
            ||
            (rotationIndicator.value >= 270f && rotationIndicator.value < 360f)
            ),
        enter = fadeIn(),
        exit = fadeOut()
      ) {
        Text(
          modifier = Modifier.fillMaxWidth().align(Alignment.Center),
          text = "Hold",
          style = TextStyle(
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
          )
        )
      }

      AnimatedVisibility(
        visible = (rotationIndicator.value >= 0f && rotationIndicator.value < 90f),
        enter = fadeIn(),
        exit = fadeOut()
      ) {
        Text(
          modifier = Modifier.fillMaxWidth().align(Alignment.Center),
          text = "Breathe In",
          style = TextStyle(
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
          )
        )
      }

      AnimatedVisibility(
        visible = (rotationIndicator.value >= 180f && rotationIndicator.value < 270f),
        enter = fadeIn(),
        exit = fadeOut()
      ) {
        Text(
          modifier = Modifier.fillMaxWidth().align(Alignment.Center),
          text = "Breathe Out",
          style = TextStyle(
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
          ),
        )
      }

    }

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .align(Alignment.BottomCenter)
    ) {
      AdityaBhawsar(
        Modifier
          .align(Alignment.Center)
          .background(Color.LightGray)
      )
    }
  }
}

fun runDialAnimation(
  coroutineScope: CoroutineScope,
  rotationIndicator: Animatable<Float, AnimationVector1D>,
  dialScale: Animatable<Float, AnimationVector1D>
) {
  coroutineScope.launch {

    coroutineScope.launch {
      rotationIndicator.animateTo(
        targetValue = 360f,
        animationSpec = tween(
          durationMillis = 10000,
          easing = LinearEasing
        )
      )
      rotationIndicator.snapTo(0f)
    }

    dialScale.animateTo(
      targetValue = 1.25f,
      animationSpec = tween(
        durationMillis = 2500,
        easing = LinearEasing
      )
    )

    delay(2500)

    dialScale.animateTo(
      targetValue = 1f,
      animationSpec = tween(
        durationMillis = 2500,
        easing = LinearEasing
      )
    )

    delay(2500)


    runDialAnimation(
      coroutineScope,
      rotationIndicator,
      dialScale
    )
  }
}
