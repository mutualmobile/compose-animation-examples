package dev.baseio.composeplayground.ui.animations.adityabhawsar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.AdityaBhawsar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircleSquareAnimation(modifier: Modifier = Modifier) {

  val rotationBox = remember {
    Animatable(0f)
  }
  val rotationCircle = remember {
    Animatable(0f)
  }

  LaunchedEffect(key1 = true){
    runAnimation(
      coroutineScope = this,
      rotationBox = rotationBox,
      rotationCircle = rotationCircle
    )
  }

  Box(
    modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {

    Row(modifier = Modifier
      .size(300.dp)
      .rotate(rotationBox.value)
      .align(Alignment.Center)) {
      Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(0.5f)) {
        Canvas(modifier = Modifier
          .fillMaxHeight(0.5f)
          .fillMaxWidth()
          .rotate(rotationCircle.value)
          .padding(12.dp), onDraw = {
          drawCircle(Color.White)
          drawRect(
            color = Color.Black,
            topLeft = Offset(size.width/2, size.height/2),
            size = Size(size.width/2, size.height/2)
          )
        })
        Canvas(modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth()
          .rotate(rotationCircle.value)
          .padding(12.dp), onDraw = {
          drawCircle(Color.White)
          drawRect(
            color = Color.Black,
            topLeft = Offset(size.width/2, 0f),
            size = Size(size.width/2, size.height/2)
          )
        })
      }
      Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Canvas(modifier = Modifier
          .fillMaxHeight(0.5f)
          .fillMaxWidth()
          .rotate(rotationCircle.value)
          .padding(12.dp), onDraw = {
          drawCircle(Color.White)
          drawRect(
            color = Color.Black,
            topLeft = Offset(0f, size.height/2),
            size = Size(size.width/2, size.height/2)
          )
        })
        Canvas(modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth()
          .rotate(rotationCircle.value)
          .padding(12.dp), onDraw = {
          drawCircle(Color.White)
          drawRect(
            color = Color.Black,
            topLeft = Offset(0f, 0f),
            size = Size(size.width/2, size.height/2)
          )
        })
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
          .background(Color.LightGray))
    }
  }
}

fun runAnimation(
  coroutineScope: CoroutineScope,
  rotationBox: Animatable<Float, AnimationVector1D>,
  rotationCircle: Animatable<Float, AnimationVector1D>
){
  coroutineScope.launch {

    delay(1000)

    rotationBox.animateTo(
      90f,
      tween(easing = FastOutSlowInEasing, durationMillis = 1000)
    )

    delay(500)

    coroutineScope.launch {
      rotationBox.animateTo(
        0f,
        tween(easing = FastOutSlowInEasing, durationMillis = 1000)
      )
    }

    rotationCircle.animateTo(
      360f,
      tween(easing = FastOutSlowInEasing, durationMillis = 1000)
    )

    rotationCircle.snapTo(0f)

    runAnimation(
      coroutineScope = this,
      rotationBox = rotationBox,
      rotationCircle = rotationCircle
    )
  }
}