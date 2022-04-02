package dev.baseio.composeplayground.ui.animations

import dev.baseio.composeplayground.contributors.AdityaBhawsar
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R

@Composable
fun BreatheAnimation(modifier: Modifier = Modifier) {

  Box(
    modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {

    Column(modifier = Modifier
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
          .background(Color.LightGray))
    }
  }
}

@Composable
fun AnimatedCircles(numCircles :Int){
  Box(modifier = Modifier.fillMaxWidth()) {
    for (circleNumber in 0 until numCircles){
      AnimatedCircle(
        modifier = Modifier
          .padding(16.dp)
          .align(Alignment.Center),
        rotationQuota = 360f/numCircles,
        circleNumber = circleNumber
      )
    }
  }
}

@Composable
fun AnimatedCircle(
  modifier: Modifier,
  rotationQuota : Float,
  circleNumber : Int
){

  val circleRotation = remember { Animatable(0f) }
  val circleScale = remember { Animatable(0.1f) }

  LaunchedEffect(circleRotation){
    circleRotation.animateTo(
      targetValue = rotationQuota * circleNumber,
      animationSpec = infiniteRepeatable(
        tween(5000, easing = LinearEasing),
        repeatMode = RepeatMode.Reverse
      )
    )
  }

  LaunchedEffect(circleScale){
    circleScale.animateTo(
      targetValue = 1f,
      animationSpec = infiniteRepeatable(
        tween(5000, easing = LinearEasing),
        repeatMode = RepeatMode.Reverse
      )
    )
  }

  Column(modifier = modifier
    .rotate(circleRotation.value)
    .scale(circleScale.value),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Canvas(modifier = Modifier
      .size(120.dp)
      .alpha(0.3f)){
      drawCircle(color = Color.Cyan)
    }
    Canvas(modifier = Modifier
      .size(125.dp)
      .alpha(0f)){
      drawRect(color = Color.Red)
    }
  }
}

@Composable
fun AnimatedText() {
  Text(text = "Breathe In...", style = TextStyle(color = Color.LightGray))
}
