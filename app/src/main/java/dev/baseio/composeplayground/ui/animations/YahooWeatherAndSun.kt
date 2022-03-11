package dev.baseio.composeplayground.ui.animations

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
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
      Text(
        text = stringResource(id = R.string.sun_moon),
        style = Typography.h5.copy(color = Color.White),
      )


      Box() {
        PathArcCanvas()
        Text(
          text = "5:44 AM",
          style = Typography.caption.copy(color = Color.White),
          modifier = Modifier.offset(y = 160.dp, x = (-20).dp)
        )

        Text(
          text = "20:01 PM",
          style = Typography.caption.copy(color = Color.White),
          modifier = Modifier.offset(y = 160.dp, x = (270).dp)
        )
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
private fun PathArcCanvas() {
  Canvas(modifier = Modifier.size(300.dp), onDraw = {
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