package dev.baseio.composeplayground.ui.animations.adityabhawsar

import android.graphics.Paint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.AdityaBhawsar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FiSplashAnimation() {

  val ballOffset = remember { Animatable(-280f) }
  val ballScale = remember { Animatable(0f) }
  val textAlpha = remember { Animatable(0f) }
  val textScale = remember { Animatable(1.35f) }
  val circleAlpha = remember { Animatable(1f) }
  val squareAlpha = remember { Animatable(0f) }
  val squareRotation = remember { Animatable(45f) }
  val heightRatio = remember { Animatable(.27f) }

  LaunchedEffect(key1 = true){
    runFiAnimation(
      this,
      ballOffset,
      ballScale,
      textScale,
      textAlpha,
      circleAlpha,
      squareAlpha,
      squareRotation,
      heightRatio
    )
  }

  Box(
    Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {

    Canvas(
      modifier = Modifier
        .size(16.dp)
        .scale(ballScale.value)
        .align(Alignment.Center)
        .offset(y = ballOffset.value.dp),
      onDraw = {
        drawCircle(color = Color(0xFF00B899))
      }
    )

    Canvas(
      modifier = Modifier
        .size(100.dp)
        .alpha(textAlpha.value)
        .scale(textScale.value)
        .align(Alignment.Center)
    ){

      val fPath = Path().let {
        it.moveTo( x= this.size.width.times(.50f) , y= this.size.height.times(.25f))
        it.lineTo(x = this.size.width.times(0.30f), y = this.size.height.times(.25f))
        it.quadraticBezierTo(
          x1 = this.size.width.times(0.07f), y1 = this.size.height.times(.25f),
          x2 = this.size.width.times(0.07f), y2 = this.size.height.times(.45f)
        )
        it.lineTo(x = this.size.width.times(0.07f), y = this.size.height.times(.95f))
        it.lineTo(x = this.size.width.times(0.25f), y = this.size.height.times(.95f))
        it.lineTo(x = this.size.width.times(0.25f), y = this.size.height.times(.85f))
        it.quadraticBezierTo(
          x1 = this.size.width.times(0.25f), y1 = this.size.height.times(0.70f),
          x2 = this.size.width.times(0.35f), y2 = this.size.height.times(0.70f)
        )
        it.lineTo(x = this.size.width.times(0.5f), y = this.size.height.times(.70f))
        it.lineTo(x = this.size.width.times(0.5f), y = this.size.height.times(.55f))
        it.lineTo(x = this.size.width.times(0.25f), y = this.size.height.times(.55f))
        it.lineTo(x = this.size.width.times(0.25f), y = this.size.height.times(.47f))
        it.quadraticBezierTo(
          x1 = this.size.width.times(0.25f), y1 = this.size.height.times(0.4f),
          x2 = this.size.width.times(0.4f), y2 = this.size.height.times(0.4f)
        )
        it.lineTo(x = this.size.width.times(0.5f), y = this.size.height.times(.4f))
        it.close()
        it
      }

      val iPath = Path().let {
        it.moveTo(x = this.size.width.times(0.6f), y = this.size.height.times(.57f))
        it.lineTo(x = this.size.width.times(0.8f), y = this.size.height.times(.57f))
        it.lineTo(x = this.size.width.times(0.8f), y = this.size.height.times(.7f))
        it.quadraticBezierTo(
          x1 = this.size.width.times(0.8f), y1 = this.size.height.times(0.77f),
          x2 = this.size.width.times(0.9f), y2 = this.size.height.times(0.77f)
        )
        it.lineTo(x = this.size.width.times(0.9f), y = this.size.height.times(.95f))
        it.lineTo(x = this.size.width.times(0.77f), y = this.size.height.times(.95f))
        it.quadraticBezierTo(
          x1 = this.size.width.times(0.6f), y1 = this.size.height.times(0.95f),
          x2 = this.size.width.times(0.6f), y2 = this.size.height.times(0.75f)
        )
        it.close()
        it
      }

      drawPath(path = fPath, color = Color.White)

      drawPath(path = iPath, color = Color.White)

      drawCircle(
        alpha = circleAlpha.value,
        color = Color.White,
        radius = 27f,
        center = Offset(x = this.size.width.times(.7f), y = this.size.height.times(0.10f + heightRatio.value))
      )

      rotate(
        degrees = squareRotation.value,
        pivot = Offset(this.size.width.times(.7f), this.size.height.times(0.10f + heightRatio.value))
      ) {
        drawRoundRect(
          alpha = squareAlpha.value,
          cornerRadius = CornerRadius(
            x= 4.dp.toPx(),
            y= 4.dp.toPx(),
          ),
          color = Color.White,
          topLeft = Offset(this.size.width.times(.6f), this.size.height.times(heightRatio.value)),
          size = Size(54f, 54f)
        )
      }

    }

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .align(Alignment.TopCenter)
    ) {
      AdityaBhawsar(
        Modifier
          .align(Alignment.Center)
          .background(Color.LightGray)
      )
    }
  }
}

fun runFiAnimation(
  coroutineScope: CoroutineScope,
  ballOffset: Animatable<Float, AnimationVector1D>,
  ballScale: Animatable<Float, AnimationVector1D>,
  textScale: Animatable<Float, AnimationVector1D>,
  textAlpha: Animatable<Float, AnimationVector1D>,
  circleAlpha: Animatable<Float, AnimationVector1D>,
  squareAlpha: Animatable<Float, AnimationVector1D>,
  squareRotation: Animatable<Float, AnimationVector1D>,
  heightRatio: Animatable<Float, AnimationVector1D>
) {
  coroutineScope.launch {

    ballScale.snapTo(.2f)

    coroutineScope.launch {
      ballScale.animateTo(
        targetValue = 1f,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
      )
    }

    ballOffset.animateTo(
      targetValue = -300f,
      animationSpec = tween(300, easing = FastOutSlowInEasing)
    )

    coroutineScope.launch {
      ballScale.animateTo(
        targetValue = 1.75f,
        animationSpec = tween(600, easing = FastOutSlowInEasing)
      )
    }

    ballOffset.animateTo(
      targetValue = 0f,
      animationSpec = tween(600, easing = FastOutSlowInEasing)
    )

    textAlpha.snapTo(1f)

    coroutineScope.launch {
      textScale.animateTo(
        targetValue = 1f,
        animationSpec = tween(600, easing = LinearEasing)
      )
    }

    coroutineScope.launch {
      ballScale.animateTo(
        targetValue = 75f,
        animationSpec = tween( 400, easing = FastOutSlowInEasing )
      )
    }

    bounceAnimation(
      coroutineScope,
      circleAlpha,
      squareAlpha,
      heightRatio,
      squareRotation
    )
  }
}

fun bounceAnimation(
  coroutineScope: CoroutineScope,
  circleAlpha: Animatable<Float, AnimationVector1D>,
  squareAlpha: Animatable<Float, AnimationVector1D>,
  heightRatio: Animatable<Float, AnimationVector1D>,
  squareRotation: Animatable<Float, AnimationVector1D>
) {
  coroutineScope.launch {

    heightRatio.animateTo(
      targetValue = 0f,
      animationSpec = tween(300, easing = FastOutSlowInEasing)
    )

    delay(50)

    heightRatio.animateTo(
      targetValue = .27f,
      animationSpec = tween(300, easing = FastOutLinearInEasing)
    )

    circleAlpha.snapTo(0f)
    squareAlpha.snapTo(1f)

    coroutineScope.launch {
      squareRotation.animateTo(
        targetValue = 135f,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
      )
    }

    heightRatio.animateTo(
      targetValue = 0f,
      animationSpec = tween(300, easing = FastOutSlowInEasing)
    )

    delay(50)

    coroutineScope.launch {
      squareRotation.animateTo(
        targetValue = 225f,
        animationSpec = tween(300, easing = FastOutSlowInEasing)
      )
    }

    heightRatio.animateTo(
      targetValue = .27f,
      animationSpec = tween(300, easing = FastOutLinearInEasing)
    )

    squareRotation.snapTo(45f)
    circleAlpha.snapTo(1f)
    squareAlpha.snapTo(0f)

    bounceAnimation(
      coroutineScope,
      circleAlpha,
      squareAlpha,
      heightRatio,
      squareRotation
    )
  }
}
