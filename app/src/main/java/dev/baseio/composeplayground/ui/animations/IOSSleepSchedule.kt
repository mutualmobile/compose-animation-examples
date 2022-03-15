package dev.baseio.composeplayground.ui.animations

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.contributors.AnmolVerma
import dev.baseio.composeplayground.ui.theme.Typography
import kotlinx.coroutines.launch
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin


val offGray = Color(45, 44, 46)
val textSecondary = Color(157, 156, 167)


@Preview
@Composable
fun PreviewIOSSleepSchedule() {
  MaterialTheme() {
    IOSSleepSchedule()
  }
}

@Composable
fun IOSSleepSchedule() {

  var startTime by remember {
    mutableStateOf(LocalTime.of(0, 0))
  }

  var endTime by remember {
    mutableStateOf(LocalTime.of(12, 0))
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(offGray)
  ) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Spacer(modifier = Modifier.padding(16.dp))
      Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
        VerticalGroupTime(isStart = true, startTime, endTime)
        VerticalGroupTime(isStart = false, startTime = startTime, endTime = endTime)
      }

      Spacer(modifier = Modifier.padding(28.dp))


      TouchMoveControlTrack(startTime, endTime, { time ->
        startTime = time
      }) { time ->
        endTime = time
      }

      Spacer(modifier = Modifier.padding(28.dp))

      Text(
        text = "${abs(endTime.hour.minus(startTime.hour))} hr",
        style = Typography.h5.copy(color = Color.White)
      )


      Spacer(modifier = Modifier.padding(8.dp))

      Text(
        text = "This schedule meets your sleep goal.",
        style = Typography.subtitle1.copy(color = textSecondary)
      )

    }

    Box(
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .fillMaxWidth()
    ) {
      AnmolVerma(Modifier.align(Alignment.Center))
    }

  }
}

fun convertHourToAngle(startTime: LocalTime, endTime: LocalTime): Float {
  val angle = startTime.hour.times(15f)
  val endAngle = endTime.hour.times(15f)
  return endAngle.minus(angle)
}

fun convertAngleToHour(startAngle: Float): LocalTime {
  var startAngle = startAngle
  if (startAngle > 360) {
    startAngle = startAngle.minus(360f)
  }
  val hour = (startAngle / 15).toInt()
  return LocalTime.of(hour, 0)
}

@Composable
private fun TouchMoveControlTrack(
  sTime: LocalTime,
  enTime: LocalTime,
  startTime: (LocalTime) -> Unit,
  endTime: (LocalTime) -> Unit
) {


  val constraintsScope = rememberCoroutineScope()

  val clockRadius = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(3.5).dp.toPx()
  }

  val clockSize = LocalConfiguration.current.screenWidthDp.div(3.5).dp

  val knobTrackStrokeWidth = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(6).dp.toPx()
  }

  val knobStrokeWidth = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(8).dp.toPx()
  }

  var shapeCenter by remember {
    mutableStateOf(Offset.Zero)
  }

  var radius by remember {
    mutableStateOf(0f)
  }

  val sweepAngleForKnob = remember {
    mutableStateOf(convertHourToAngle(sTime, enTime))
  }

  var angle by remember {
    mutableStateOf(0.0f)
  }

  var startIconOffset by remember {
    mutableStateOf(Offset.Zero)
  }

  val reduceOffsetIcon = with(LocalDensity.current) {
    24.dp.toPx()
  }


  val haptic = LocalHapticFeedback.current

  Box(Modifier) {
    Canvas(modifier = Modifier
      .size(300.dp), onDraw = {
      drawKnobBackground(knobTrackStrokeWidth)
      drawClockCircle(clockRadius, shapeCenter)
    })

    DrawTicks(clockSize)

    Canvas(modifier = Modifier
      .size(300.dp)
      .pointerInput(Unit) {
        constraintsScope.launch {
          detectDragGestures { change, dragAmount ->
            startIconOffset += dragAmount
            angle = getRotationAngle(change.position, shapeCenter).toFloat()
            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            change.consumeAllChanges()
          }
        }
      }, onDraw = {
      shapeCenter = center

      radius = size.minDimension / 2


      // start and end time
      startTime(convertAngleToHour(angle))
      endTime(convertAngleToHour(angle + sweepAngleForKnob.value))

      drawRotatingKnob(angle, knobStrokeWidth, sweepAngleForKnob.value)

    })

    Box(Modifier.size(300.dp)) {
      KnobIcon(reduceOffsetIcon, shapeCenter, true, angle, radius) {
        sweepAngleForKnob.value = it.toFloat()
      }
      KnobIcon(reduceOffsetIcon, shapeCenter, false, angle + sweepAngleForKnob.value, radius) {
        sweepAngleForKnob.value = it.toFloat()
      }
    }
  }

}

@Composable
private fun BoxScope.DrawTicks(clockSize: Dp) {
  Box(
    modifier = Modifier
      .align(Alignment.Center)
      .size(clockSize)
  ) {
    repeat(120) {
      Tick(it)
    }
  }
}

@Composable
fun BoxScope.Tick(tick: Int) {
  val angle = 360f / 120f * tick
  Text(
    text = "|",
    modifier = Modifier
      .align(Alignment.Center)
      .rotate(angle) // rotate your number by 'angle' and now 'up' is towards the numbers final position
      .offset(0.dp, (-110).dp),
    style = Typography.caption.copy(
      fontSize = if (tick % 5 == 0) 8.sp else 2.sp,
      color = if (tick % 5 == 0) textSecondary else Color.White
    )
  ) //positive y is down on the screen. -100 goes "up" in the direction of angle

}

@Composable
private fun KnobIcon(
  reduceOffsetIcon: Float,
  shapeCenter: Offset,
  isStart: Boolean,
  angleKnob: Float,
  radius: Float,
  angle: (Double) -> Unit
) {
  // start icon offset
  val startIconX = (shapeCenter.x + cos(Math.toRadians(angleKnob.toDouble())) * radius).toFloat()
  val startIconY = (shapeCenter.y + sin(Math.toRadians(angleKnob.toDouble())) * radius).toFloat()
  val startIconOffset = Offset(startIconX, startIconY)

  val constraintsScope = rememberCoroutineScope()
  SleepBedTimeIcon(isStart,
    Modifier
      .pointerInput(Unit) {
        constraintsScope.launch {
          detectDragGestures(onDrag = { change, dragAmount ->
            angle.invoke(getRotationAngle(change.position, shapeCenter))
            change.consumeAllChanges()
          })
        }
      }
      .offset {
        IntOffset(
          startIconOffset.x
            .toInt()
            .minus(reduceOffsetIcon / 2)
            .toInt(),
          startIconOffset.y
            .toInt()
            .minus(reduceOffsetIcon / 2)
            .toInt()
        )
      })
}

private fun DrawScope.drawClockCircle(clockRadius: Float, shapeCenter: Offset) {
  drawCircle(color = offGray, radius = clockRadius)
  drawClockNumerals(shapeCenter, clockRadius)
}

private fun DrawScope.drawClockNumerals(
  shapeCenter: Offset,
  clockRadius: Float
) {
  val labels = clockLabels()

  labels.forEachIndexed { index, it ->
    val paint = normalTextPaint()

    val boldPaint = boldTextPaint()
    val rect = Rect()
    paint.getTextBounds(it, 0, it.length, rect);
    val angle = index * Math.PI * 2 / 24 - (Math.PI / 2)

    val x = (shapeCenter.x + cos(angle) * clockRadius.times(0.75f) - rect.width() / 2).toFloat()
    val y =
      (shapeCenter.y + sin(angle) * clockRadius.times(0.75f) + rect.height() / 2).toFloat()
    if (isClockBoldNeeded(it) || it.toInt() % 2 == 0
    ) {
      drawContext.canvas.nativeCanvas.drawText(
        it,
        x,
        y, if (isClockBoldNeeded(it)) boldPaint else paint
      )
    }

  }
}

private fun boldTextPaint(): Paint {
  return Paint().apply {
    color = android.graphics.Color.WHITE
    textSize = 32f
    this.isFakeBoldText = true
  }
}

private fun normalTextPaint(): Paint {
  return Paint().apply {
    color = android.graphics.Color.LTGRAY
    textSize = 32f
  }
}

private fun isClockBoldNeeded(it: String) =
  it.contains("AM", ignoreCase = true) || it.contains(
    "PM",
    ignoreCase = true
  )

private fun clockLabels() =
  arrayOf(
    "12AM",
    "1",
    "2",
    "3",
    "4",
    "5",
    "6AM",
    "7",
    "8",
    "9",
    "10",
    "11",
    "12PM",
    "1",
    "2",
    "3",
    "4",
    "5",
    "6PM",
    "7",
    "8",
    "9",
    "10",
    "11"
  )

private fun DrawScope.drawKnobBackground(knobTrackStrokeWidth: Float) {
  drawArc(
    Color(1, 0, 0), 0f, 360f,
    useCenter = true, style = Stroke(width = knobTrackStrokeWidth)
  )
}

private fun DrawScope.drawRotatingKnob(
  angle: Float,
  knobStrokeWidth: Float,
  sweepAngleForKnob: Float
) {
  drawArc(
    color = offGray,
    startAngle = angle,
    sweepAngle = sweepAngleForKnob,
    false,
    style = Stroke(width = knobStrokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
  )
}

@Composable
fun VerticalGroupTime(isStart: Boolean, startTime: LocalTime, endTime: LocalTime) {
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
      text = if (isStart) startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) else endTime.format(
        DateTimeFormatter.ofPattern("hh:mm a")
      ),
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
  return atan2(dy, dx).toDouble()
}