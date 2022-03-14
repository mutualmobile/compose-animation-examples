package dev.baseio.composeplayground.ui.animations

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.ui.theme.Typography
import kotlinx.coroutines.launch
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


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

      Spacer(modifier = Modifier.padding(28.dp))


      TouchMoveControlTrack()

      Spacer(modifier = Modifier.padding(28.dp))

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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun TouchMoveControlTrack() {


  val constraintsScope = rememberCoroutineScope()

  val clockRadius = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(3.5).dp.toPx()
  }

  var shapeCenter by remember {
    mutableStateOf(Offset.Zero)
  }

  var angle by remember {
    mutableStateOf(0.0)
  }

  var starIconOffset by remember {
    mutableStateOf(IntOffset.Zero)
  }

  var endIconOffset by remember {
    mutableStateOf(IntOffset.Zero)
  }

  Box(Modifier) {
    Canvas(modifier = Modifier
      .pointerInput(Unit) {
        constraintsScope.launch {
          detectDragGestures(onDrag = { change, dragAmount ->
            // TODO fix this logic for dragging the handle
            // the handle works fine when it's size is small, if the size if big,
            // then the method getRotationAngle fails to calculate the angle
            angle = getRotationAngle(change.position, shapeCenter)
            val theta = radians(change.position, shapeCenter)
            val x = (shapeCenter.x + cos(theta) * 218f)
            val y = (shapeCenter.y + sin(theta) * 218f)
            starIconOffset = IntOffset(x.toInt(), y.toInt())

            change.consumeAllChanges()
          })
        }
      }
      .size(300.dp), onDraw = {
      shapeCenter = center

      drawKnobBackground()
      drawClockCircle(clockRadius)
      drawRotatingKnob(angle)
    })

    Box(Modifier.size(300.dp)) {
      SleepBedTimeIcon(true, Modifier.offset {
        starIconOffset
      })
      SleepBedTimeIcon(false, Modifier.offset {
        endIconOffset
      })
    }
  }

}

private fun DrawScope.drawClockCircle(clockRadius: Float) {
  drawCircle(color = offGray, radius = clockRadius)
}

private fun DrawScope.drawKnobBackground() {
  drawArc(
    Color(1, 0, 0), 0f, 360f,
    useCenter = true, style = Stroke(width = 178f)
  )
}

private fun DrawScope.drawRotatingKnob(angle: Double) {
  drawArc(
    offGray,
    angle.toFloat(),
    120f,
    false,
    style = Stroke(width = 124f, cap = StrokeCap.Round, join = StrokeJoin.Round)
  )
}

@Composable
fun VerticalGroupTime(isStart: Boolean) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
      SleepBedTimeIcon(isStart, Modifier)
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

@Composable
private fun SleepBedTimeIcon(isStart: Boolean, modifier: Modifier = Modifier) {
  Icon(
    painter = painterResource(isStart),
    tint = textSecondary,
    contentDescription = null, modifier = modifier
  )
}

@Composable
private fun painterResource(isStart: Boolean) =
  if (isStart) painterResource(id = R.drawable.ic_bed) else painterResource(id = R.drawable.ic_alarm)

private fun getRotationAngle(currentPosition: Offset, center: Offset): Double {
  val theta = radians(currentPosition, center)

  var angle = Math.toDegrees(theta)

  if (angle < 0) {
    angle += 360.0
  }
  return angle
}

private fun radians(
  currentPosition: Offset,
  center: Offset
): Double {
  val (dx, dy) = currentPosition - center
  val theta = atan2(dy, dx).toDouble()
  return theta
}