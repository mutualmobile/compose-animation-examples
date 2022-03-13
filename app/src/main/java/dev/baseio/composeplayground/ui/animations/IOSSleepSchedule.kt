package dev.baseio.composeplayground.ui.animations

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.ui.theme.Typography

val offGray = Color(45, 44, 46)
val textSecondary = Color(157, 156, 167)

@Composable
fun IOSSleepSchedule() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(offGray)
  ) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Spacer(modifier = Modifier.padding(16.dp))
      Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
        VerticalGroupTime(isStart = true)
        VerticalGroupTime(isStart = false)
      }

      Spacer(modifier = Modifier.padding(16.dp))


      TouchMoveControlTrack()

      Spacer(modifier = Modifier.padding(16.dp))

      Text(
        text = "8 hr",
        style = Typography.h5.copy(color = Color.White)
      )


      Spacer(modifier = Modifier.padding(8.dp))

      Text(
        text = "This schedule meets your sleep goal.",
        style = Typography.subtitle1.copy(color = textSecondary)
      )

    }

  }
}

@Composable
private fun TouchMoveControlTrack() {

  val centerOffset = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(3).dp.toPx()
  }

  Box() {
    Canvas(modifier = Modifier
      .size(360.dp)
      .padding(24.dp), onDraw = {
      drawArc(Color(1, 0, 0), 0f, 360f, useCenter = true, style = Stroke(width = 148f))

      drawCircle(offGray, radius = centerOffset)

    })


  }

}

@Composable
fun VerticalGroupTime(isStart: Boolean) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
      Icon(
        painter = if (isStart) painterResource(id = R.drawable.ic_bed) else painterResource(id = R.drawable.ic_alarm),
        tint = textSecondary,
        contentDescription = null
      )
      Spacer(modifier = Modifier.padding(end = 2.dp))
      Text(
        text = if (isStart) "BEDTIME" else "WAKE UP",
        style = Typography.subtitle2.copy(color = textSecondary)
      )
    }
    Spacer(modifier = Modifier.padding(top = 2.dp))
    Text(
      text = if (isStart) "9:30 PM" else "5:30 AM",
      style = Typography.h5.copy(color = Color.White)
    )
    Spacer(modifier = Modifier.padding(top = 2.dp))
    Text(
      text = if (isStart) "Today" else "Tomorrow",
      style = Typography.subtitle2.copy(color = textSecondary)
    )
  }
}