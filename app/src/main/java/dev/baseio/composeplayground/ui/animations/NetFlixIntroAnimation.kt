package dev.baseio.composeplayground.ui.animations

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

val baseColor = Color(0xffe40913)

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
  }
}

@Composable
fun BoxScope.DrawNetflix() {
  val netflixHeight = with(LocalDensity.current) {
    125.dp.toPx()
  }
  Canvas(modifier = Modifier.align(Alignment.Center), onDraw = {
    drawN(netflixHeight)

  })
}

private fun DrawScope.drawN(netflixHeight: Float) {
  // draw N
  // First Draw last |
  var initialX = 0f
  repeat(31) {
    drawLine(
      brush = Brush.sweepGradient(
        listOf(baseColor, baseColor),
      ),
      start = Offset(initialX, 0f),
      end = Offset(initialX, netflixHeight)
    )
    initialX += 2.5f
  }

  // Now Draw middle \
  var initialOfMiddle = 0f
  rotate(
    -25f,
    center.copy(
      x = initialX.times(-0.50f),
      y = netflixHeight.times(0.50f)
    )
  ) {
    repeat(31) {
      drawLine(
        brush = Brush.sweepGradient(
          listOf(baseColor, baseColor),
        ),
        start = Offset(initialOfMiddle, 0f),
        end = Offset(initialOfMiddle, netflixHeight)
      )
      initialOfMiddle -= 2.5f
    }
  }

  var initialOfFirst = initialOfMiddle
  repeat(31) {
    drawLine(
      brush = Brush.sweepGradient(
        listOf(baseColor, baseColor),
      ),
      start = Offset(initialOfFirst, 0f),
      end = Offset(initialOfFirst, netflixHeight)
    )
    initialOfFirst -= 2.5f
  }
}
