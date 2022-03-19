package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.AnmolVerma
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val baseColor = Color(0xffe40913)

/**
 * Inspiration
 * https://dev.to/claudiobonfati/netflix-intro-animation-pure-css-1m0c
 */
@Composable
fun NetflixIntroAnimation() {

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {
    Box(
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxWidth()
    )
    {
      DrawNetflix()
    }

    Box(
      modifier = Modifier
        .align(Alignment.BottomEnd)
    ) {
      AnmolVerma(
        Modifier
          .padding(24.dp)
          .align(Alignment.Center))
    }

  }
}

@Composable
fun BoxScope.DrawNetflix() {
  val netflixHeight = with(LocalDensity.current) {
    125.dp.toPx()
  }

  val colorFirstN = remember {
    Animatable(1f)
  }
  val colorSecondN = remember {
    Animatable(1f)
  }
  val colorMiddleN = remember {
    Animatable(1f)
  }

  val scaleNetflix = remember {
    Animatable(1f)
  }

  LaunchedEffect(key1 = true, block = {
    delay(500)
    launch {
      scaleNetflix.animateTo(3f, tween(durationMillis = 3900))
    }
    colorSecondN.animateTo(0f, tween(durationMillis = 1000))
    colorMiddleN.animateTo(0f, tween(durationMillis = 800))
    colorFirstN.animateTo(0f, tween(durationMillis = 600))
  })

  Canvas(modifier = Modifier
    .scale(scaleNetflix.value)
    .align(Alignment.Center), onDraw = {
    translate(left = -100f, top = -netflixHeight / 2) {
      drawN(netflixHeight, colorFirstN.value, colorMiddleN.value, colorSecondN.value)
    }

  })
}

private fun DrawScope.drawN(
  netflixHeight: Float,
  colorFirstN: Float,
  colorMiddleN: Float,
  colorSecondN: Float
) {
  // draw N
  // First Draw last |
  var initialX = 0f
  val lineSpacing = 1f
  val totalLines = 75
  val nRotation = -25f

  val centerOfNRotation = center.copy(
    x = center.x.plus(totalLines.times(1.5f)),
    y = lineSpacing.times(totalLines.times(2.5f))
  )

  repeat(totalLines) {
    drawLine(
      brush = Brush.verticalGradient(
        listOf(
          Color.Black,
          baseColor.copy(alpha = colorFirstN),
        ), startY = 0f, endY = netflixHeight.times(colorFirstN)
      ),
      start = Offset(initialX, 0f),
      end = Offset(initialX, netflixHeight.times(colorFirstN))
    )
    initialX += lineSpacing
  }


  // Now Draw middle \
  var initialOfMiddle = initialX
  rotate(
    nRotation,
    centerOfNRotation
  ) {
    repeat(totalLines) {
      drawLine(
        brush = Brush.verticalGradient(
          listOf(
            Color.Black,
            baseColor.copy(alpha = colorMiddleN),
          ), startY = 0f, endY = netflixHeight.times(colorFirstN)
        ),
        start = Offset(initialOfMiddle, 0f),
        end = Offset(initialOfMiddle, netflixHeight.times(colorFirstN))
      )
      initialOfMiddle += lineSpacing
    }
  }

  var initialOfFirst = initialOfMiddle
  repeat(totalLines) {
    drawLine(
      brush = Brush.verticalGradient(
        listOf(
          Color.Black,
          baseColor.copy(alpha = colorSecondN),
        ), startY = 0f, endY = netflixHeight.times(colorFirstN)
      ),
      start = Offset(initialOfFirst, 0f),
      end = Offset(initialOfFirst, netflixHeight.times(colorFirstN))
    )
    initialOfFirst += lineSpacing
  }
}
