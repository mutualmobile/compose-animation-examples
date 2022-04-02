package dev.baseio.composeplayground.ui.animations.adityabhawsar

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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R

@Composable
fun MicAnimation(modifier: Modifier = Modifier) {

  val alphaCircle = remember { Animatable(0f) }
  val scaleFactorLightGreyBg = remember { Animatable(1f) }
  val scaleFactorGreyBg = remember { Animatable(1f) }
  val scaleFactorRedBg = remember { Animatable(1f) }

  LaunchedEffect(alphaCircle){
    alphaCircle.animateTo(
      targetValue = 1f,
      animationSpec = infiniteRepeatable(
        tween(1500, easing = LinearEasing),
        repeatMode = RepeatMode.Reverse
      )
    )
  }

  LaunchedEffect(scaleFactorLightGreyBg){
    scaleFactorLightGreyBg.animateTo(
      targetValue = 3f,
      animationSpec = infiniteRepeatable(
        tween(700, easing = FastOutLinearInEasing),
        repeatMode = RepeatMode.Reverse
      )
    )
  }

  LaunchedEffect(scaleFactorGreyBg){
    scaleFactorGreyBg.animateTo(
      targetValue = 2f,
      animationSpec = infiniteRepeatable(
        tween(1000, easing = LinearOutSlowInEasing),
        repeatMode = RepeatMode.Reverse
      )
    )
  }

  LaunchedEffect(scaleFactorRedBg){
    scaleFactorRedBg.animateTo(
      targetValue = 1.2f,
      animationSpec = infiniteRepeatable(
        tween(500, easing = FastOutSlowInEasing),
        repeatMode = RepeatMode.Reverse
      )
    )
  }

  Box(
    modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {

    Surface(
      shape = CircleShape,
      color = Color.Black,
      border = BorderStroke(3.dp, Color.LightGray),
      modifier = Modifier
        .size(210.dp)
        .alpha(alphaCircle.value)
        .align(Alignment.Center)
    ) {}

    Canvas(
      modifier = Modifier
        .size(70.dp)
        .align(Alignment.Center)
        .scale(scaleFactorLightGreyBg.value)
        .alpha(0.3f),
      onDraw = {
        drawCircle(color = Color.LightGray)
      }
    )

    Canvas(
      modifier = Modifier
        .size(70.dp)
        .align(Alignment.Center)
        .scale(scaleFactorGreyBg.value),
      onDraw = {
        drawCircle(color = Color.Gray)
      }
    )

    Canvas(
      modifier = Modifier
        .size(70.dp)
        .align(Alignment.Center)
        .scale(scaleFactorRedBg.value),
      onDraw = {
        drawCircle(color = Color.Red)
      }
    )

    FloatingActionButton(
      onClick = {},
      content = {
        Icon(
          painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_mic_white_40),
          contentDescription = "Mic"
        )
      },
      elevation = FloatingActionButtonDefaults.elevation(0.dp),
      backgroundColor = Color.Red,
      contentColor = Color.White,
      modifier = Modifier
        .size(70.dp)
        .align(Alignment.Center)
        .scale(1f)
    )

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