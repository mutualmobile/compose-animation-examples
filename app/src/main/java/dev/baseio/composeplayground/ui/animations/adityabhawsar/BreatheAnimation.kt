package dev.baseio.composeplayground.ui.animations

import dev.baseio.composeplayground.contributors.AdityaBhawsar
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BreatheAnimation(modifier: Modifier = Modifier) {

  Box(
    modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.Center),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      AnimatedCircles(6)
      Spacer(modifier = Modifier.padding(24.dp))
      AnimatedText()
    }

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .align(Alignment.BottomEnd)
    ) {
      AdityaBhawsar(
        Modifier
          .align(Alignment.Center)
          .background(Color.LightGray)
      )
    }
  }
}

@Composable
fun AnimatedCircles(numCircles: Int) {
  Box(modifier = Modifier.fillMaxWidth()) {
    for (circleNumber in 0 until numCircles) {
      AnimatedCircle(
        modifier = Modifier
          .padding(16.dp)
          .align(Alignment.Center),
        rotationQuota = 360f / numCircles,
        circleNumber = circleNumber
      )
    }
  }
}

@Composable
fun AnimatedCircle(
  modifier: Modifier,
  rotationQuota: Float,
  circleNumber: Int
) {

  val circleRotation = remember { Animatable(0f) }
  val circleScale = remember { Animatable(0.1f) }

  LaunchedEffect(key1 = true) {
    runAnimatedCircleScale(
      coroutineScope = this,
      scaleFactor = circleScale
    )
  }

  LaunchedEffect(key1 = true) {
    runAnimatedCircleRotation(
      coroutineScope = this,
      rotationFactor = circleRotation,
      rotationTarget = rotationQuota * circleNumber,
    )
  }

  Column(
    modifier = modifier
      .rotate(circleRotation.value)
      .scale(circleScale.value),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Canvas(
      modifier = Modifier
        .size(120.dp)
        .alpha(0.3f)
    ) {
      drawCircle(color = Color.Cyan)
    }
    Canvas(
      modifier = Modifier
        .size(125.dp)
        .alpha(0f)
    ) {
      drawRect(color = Color.Red)
    }
  }
}

fun runAnimatedCircleRotation(
  coroutineScope: CoroutineScope,
  rotationFactor: Animatable<Float, AnimationVector1D>,
  rotationTarget: Float
) {
  coroutineScope.launch {

    rotationFactor.animateTo(
      targetValue = rotationTarget,
      animationSpec = tween(5000, easing = LinearEasing)
    )

    delay(500)

    rotationFactor.animateTo(
      targetValue = 0f,
      animationSpec = tween(5000, easing = LinearEasing)
    )

    delay(500)

    runAnimatedCircleRotation(
      coroutineScope = coroutineScope,
      rotationFactor = rotationFactor,
      rotationTarget = rotationTarget
    )
  }

}

fun runAnimatedCircleScale(
  coroutineScope: CoroutineScope,
  scaleFactor: Animatable<Float, AnimationVector1D>
) {
  coroutineScope.launch {

    scaleFactor.animateTo(
      targetValue = 1f,
      animationSpec = tween(5000, easing = LinearEasing)
    )

    delay(500)

    scaleFactor.animateTo(
      targetValue = 0.1f,
      animationSpec = tween(5000, easing = LinearEasing)
    )

    delay(500)

    runAnimatedCircleScale(coroutineScope = coroutineScope, scaleFactor = scaleFactor)
  }
}

@Composable
fun AnimatedText() {

  val animatedValue = remember { Animatable(1f)}

  LaunchedEffect(key1 = true) {
    runAnimationText(
      coroutineScope = this,
      scaleFactor = animatedValue
    )
  }

  Box(modifier = Modifier.fillMaxWidth()) {
    Text(
      text = "Breathe Out",
      textAlign = TextAlign.Center,
      style = TextStyle(color = Color.LightGray, fontSize = 20.sp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .align(Alignment.Center)
        .alpha(if(animatedValue.value == 0.1f) 0f else animatedValue.value)
    )
    Text(
      text = "Breathe In",
      textAlign = TextAlign.Center,
      style = TextStyle(color = Color.LightGray, fontSize = 20.sp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .align(Alignment.Center)
        .alpha(if(animatedValue.value >= 0.7f) 0f else 1f)
        .scale((1-animatedValue.value))
    )
  }

}

fun runAnimationText(
  coroutineScope: CoroutineScope,
  scaleFactor: Animatable<Float, AnimationVector1D>
) {
  coroutineScope.launch {

    scaleFactor.animateTo(
      targetValue = 0.1f,
      animationSpec = tween(5000, easing = LinearEasing)
    )

    delay(500)

    scaleFactor.animateTo(
      targetValue = 1f,
      animationSpec = tween(5000, easing = LinearEasing)
    )

    delay(500)

    runAnimationText(coroutineScope = coroutineScope, scaleFactor = scaleFactor)
  }
}
