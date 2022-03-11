package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.contributors.AnmolVerma
import dev.baseio.composeplayground.ui.theme.Typography

/**
 * https://github.com/amosgyamfi/swiftui-animation-library#yahoo-weather-sun--moon
 */
@Composable
fun YahooWeatherAndSun(modifier: Modifier) {

  var sunMovesAlong by remember {
    mutableStateOf(false)
  }

  var maskedRectScaledAlong by remember {
    mutableStateOf(false)
  }

  val angleRotation by animateFloatAsState(
    targetValue = if (sunMovesAlong) 145f else 20f,
    animationSpec = tween(durationMillis = 5000, delayMillis = 2000, easing = LinearEasing)
  )

  val scaleRect by animateFloatAsState(
    targetValue = if (maskedRectScaledAlong) 0.9f else 0f,
    animationSpec = tween(durationMillis = 5000, delayMillis = 2000, easing = LinearEasing)
  )

  LaunchedEffect(key1 = true) {
    sunMovesAlong = !sunMovesAlong
    maskedRectScaledAlong = !maskedRectScaledAlong
  }


  Box(
    modifier
      .background(Color(0xff112937))
  ) {

    Column(
      Modifier
        .fillMaxSize()
        .clickable {}
        .padding(4.dp),
      verticalArrangement = Arrangement.SpaceAround,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Box() {
        Text(
          text = stringResource(id = R.string.sun_moon),
          style = Typography.h5.copy(color = Color.White),
        )
        Text(
          text = "5:44 AM",
          style = Typography.caption.copy(color = Color.White),
          modifier = Modifier.offset(y = 280.dp, x = (-120).dp)
        )
        Text(
          text = "20:01 PM",
          style = Typography.caption.copy(color = Color.White),
          modifier = Modifier.offset(y = 280.dp, x = (190).dp)
        )
      }

      Box {
        PathArcCanvas(Modifier.align(Alignment.Center))

        Icon(
          painter = painterResource(id = R.drawable.ic_sun),
          contentDescription = null,
          modifier = Modifier.offset(x=26.dp,y=146.dp)
            .rotate(angleRotation),
          tint = Color(0xfff9d71c)
        )

        Box(
          Modifier
            .fillMaxWidth(fraction = 0.98f)
            .align(Alignment.Center)
            .height(1.dp)
            .background(Color.White.copy(0.5f))
            .offset(y = 160.dp)
        )



        Box(
          Modifier
            .graphicsLayer(shape = CircleShape)
            .align(Alignment.Center)
            .size(320.dp)
        ) {
          ContainedCanvas(scaleRect)
        }



      }

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
      ) {
        AnmolVerma(Modifier.align(Alignment.Center))
      }
    }
  }
}

@Composable
private fun ContainedCanvas(scaleRect: Float) {
  Canvas(
    modifier = Modifier
      .width(300.dp)
      .height(160.dp)
      .graphicsLayer(
        transformOrigin = TransformOrigin(0f, 0f),
        scaleX = scaleRect, scaleY = 1f
      ),
    onDraw = {
      drawRect(
        color = Color(0xfff9d71c),
        alpha = 0.1f,
      )
    })
}

@Composable
private fun PathArcCanvas(modifier: Modifier) {
  Canvas(modifier = modifier.size(320.dp), onDraw = {
    drawArc(
      color = Color(0xfff9d71c),
      style = Stroke(
        width = 1f,
        pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(27f, 27f))
      ), useCenter = false,
      startAngle = 180f,
      sweepAngle = 180f
    )
  })
}