package dev.baseio.composeplayground.ui.animations.adityabhawsar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.contributors.AdityaBhawsar
import kotlinx.coroutines.*
import kotlin.math.roundToInt

@Composable
fun AlarmSliderAnimation() {

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xff1f231f))
  ) {

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .align(Alignment.Center)
    )
    {
      AlarmSlider()
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
          .background(Color.LightGray)
      )
    }
  }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlarmSlider() {

  BoxWithConstraints(
    modifier = Modifier
      .fillMaxWidth()
      .height(80.dp)
  ) {

    val boxAlpha = remember { Animatable(0f) }
    val leftWeight = remember { Animatable(0.45f) }
    val rightWeight = remember { Animatable(0.45f) }
    val clockRotation = remember { Animatable(0f) }

    val swipeableState = rememberSwipeableState(0.5f) { state -> true }

    val swipeCenter: Float = (constraints.maxWidth / 2).toFloat() - ((constraints.maxHeight * 1.35f) / 2)
    val swipeRight: Float = constraints.maxWidth - (constraints.maxHeight * 1.35f)
    val swipeLeft = 0f

    LaunchedEffect(swipeableState.offset) {
        if(swipeableState.offset.value == swipeCenter) {
          boxAlpha.snapTo(0f)
          leftWeight.snapTo(0.45f)
          rightWeight.snapTo(0.45f)
          clockRotation.snapTo(0f)

          runInactiveAnimation(
            this,
            boxAlpha,
            leftWeight,
            rightWeight,
            clockRotation
          )
        } else {
          boxAlpha.snapTo(0f)
          leftWeight.snapTo(0.45f)
          rightWeight.snapTo(0.45f)
          clockRotation.snapTo(0f)
        }
    }

    val anchors = mapOf(
      swipeLeft to 0f,
      swipeCenter to 0.5f,
      swipeRight to 1f
    )

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(40.dp))
        .background(Color(0xff353a3d))
        .swipeable(
          state = swipeableState,
          anchors = anchors,
          thresholds = { _, _ -> FractionalThreshold(0.7f) },
          orientation = Orientation.Horizontal
        )
    ) {

      AnimatedVisibility(
        visible = (swipeableState.offset.value == swipeCenter),
        enter = fadeIn(),
        exit = fadeOut()
      ) {
        Row(modifier = Modifier
          .fillMaxSize()
          .align(Alignment.Center)) {

          Box(modifier = Modifier.weight(leftWeight.value))
          Box(
            modifier = Modifier
              .fillMaxHeight()
              .alpha(boxAlpha.value)
              .weight(1 - (leftWeight.value + rightWeight.value))
              .padding(8.dp)
              .clip(RoundedCornerShape(40.dp))
              .background(Color(0xff626972))
          )
          Box(modifier = Modifier.weight(rightWeight.value))
        }
      }

      Text(
        modifier = Modifier
          .align(Alignment.CenterStart)
          .padding(16.dp),
        text = "Snooze",
        style = TextStyle(
          color = Color.White,
          fontSize = 18.sp
        )
      )

      Text(
        modifier = Modifier
          .align(Alignment.CenterEnd)
          .padding(16.dp),
        text = "Stop",
        style = TextStyle(
          color = Color.White,
          fontSize = 18.sp
        )
      )

      Box(
        modifier = Modifier
          .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
          .fillMaxHeight()
          .aspectRatio(1.35f, true)
          .padding(8.dp)
          .clip(RoundedCornerShape(40.dp))
          .background(Color(0xffd6e8fb))
      ) {
        Icon(
          painter = painterResource(id = R.drawable.ic_alarm),
          modifier = Modifier
            .rotate(clockRotation.value)
            .alpha(if (swipeableState.offset.value == swipeCenter) 1f else 0f)
            .size(24.dp)
            .align(Alignment.Center),
          contentDescription = "",
          tint = Color.Black
        )

        Icon(
          painter = painterResource(id = R.drawable.ic_alarm),
          modifier = Modifier
            .rotate(rotationClock(
              swipeableState.offset.value,
              swipeCenter,
              swipeLeft,
              swipeRight
            ))
            .alpha(alphaClock(
              swipeableState.offset.value,
              swipeCenter,
              swipeLeft,
              swipeRight
            ))
            .size(24.dp)
            .align(Alignment.Center),
          contentDescription = "",
          tint = Color.Black
        )

        Icon(
          painter = painterResource(id = R.drawable.ic_check),
          modifier = Modifier
            .rotate(rotationCheck(
              swipeableState.offset.value,
              swipeCenter,
              swipeLeft,
              swipeRight
            ))
            .scale(scaleCheck(
              swipeableState.offset.value,
              swipeCenter,
              swipeLeft,
              swipeRight
            ))
            .alpha(alphaCheck(
              swipeableState.offset.value,
              swipeCenter
            ))
            .size(24.dp)
            .align(Alignment.Center),
          contentDescription = "",
          tint = Color.Black
        )
      }
    }
  }
}

fun rotationClock(
  currentPos: Float,
  center: Float,
  left: Float,
  right: Float
): Float {
  when {
    currentPos == center -> {
      return 0f
    }
    currentPos > center -> {
      // to right
      val progressMade = currentPos - center
      val total = right - center

      val per = (progressMade/total) * 100

      return if(per > 25) {
        270f
      } else {
        (per * 10.8f)
      }
    }
    currentPos < center -> {
      //to left

      val progressMade = center - currentPos
      val total = center - left

      val per = (progressMade/total) * 100

      return if(per > 25) {
        -270f
      } else {
        (per * 10.8f)*-1
      }
    }
    else -> {
      return 0f
    }
  }
}

fun alphaClock(
  currentPos: Float,
  center: Float,
  left: Float,
  right: Float
): Float {
  when {
    currentPos == center -> {
      return 0f
    }
    currentPos > center -> {
      // to right
      val progressMade = currentPos - center
      val total = right - center
      val per = (progressMade/total) * 100

      return if(per > 25) {
        0f
      } else {
        1 - (per * .04f)
      }
    }
    currentPos < center -> {
      //to left
      val progressMade = center - currentPos
      val total = center - left
      val per = (progressMade/total) * 100

      return if(per > 25) {
        0f
      } else {
        1 - (per * 0.04f)
      }
    }
    else -> {
      return 0f
    }
  }
}

fun rotationCheck(
  currentPos: Float,
  center: Float,
  left: Float,
  right: Float
): Float {
  when {
    currentPos == center -> {
      return 90f
    }
    currentPos > center -> {
      val progressMade = currentPos - center
      val total = right - center
      val per = (progressMade/total) * 100

      return if(per <= 16.5){
        90f + (per * 10.9f)
      } else if(per > 16.5 && per <= 50) {
        (270f + (90f * (per - 16.5f)/33.5f))
      } else {
        0f
      }
    }
    currentPos < center -> {
      val progressMade = center - currentPos
      val total = center - left
      val per = (progressMade/total) * 100

      return if(per <= 16.5){
        90f + ((per * 10.9f)* -1)
      } else if(per > 16.5 && per <= 50) {
        (-90f + (270f * ((per - 16.5f)/33.5f)*-1))
      } else {
        0f
      }
    }
    else -> { return 0f}
  }
}

fun alphaCheck(
  currentPos: Float,
  center: Float,
): Float {
  if(currentPos == center){
    return 0f
  }
  return 1f
}

fun scaleCheck(
  currentPos: Float,
  center: Float,
  left: Float,
  right: Float
): Float {
  when {
    currentPos == center -> {
      return 0.4f
    }
    currentPos > center -> {
      val progressMade = currentPos - center
      val total = right - center
      val per = (progressMade/total) * 100

      return if(per <= 25){
        0.4f
      } else if(per > 25 && per <= 50) {
        (0.4f + (0.6f * (per - 25)/25))
      } else {
        1f
      }
    }
    currentPos < center -> {
      val progressMade = center - currentPos
      val total = center - left
      val per = (progressMade/total) * 100
      return if(per <= 25){
        0.4f
      } else if(per > 25 && per <= 50) {
        (0.4f + (0.6f * (per - 25)/25))
      } else {
        1f
      }
    }
    else -> { return 1f }
  }
}


fun runInactiveAnimation(
  coroutineScope: CoroutineScope,
  boxAlpha: Animatable<Float, AnimationVector1D>,
  leftWeight: Animatable<Float, AnimationVector1D>,
  rightWeight: Animatable<Float, AnimationVector1D>,
  clockRotation: Animatable<Float, AnimationVector1D>
) {
  coroutineScope.launch{

    boxAlpha.snapTo(1f)

    leftWeight.animateTo(
      targetValue = 0.01f,
      animationSpec = tween(750, easing = LinearEasing)
    )

    delay(50)

    coroutineScope.launch {
      clockRotation.animateTo(
        targetValue = 30f,
        animationSpec = tween(50, easing = LinearEasing)
      )

      clockRotation.animateTo(
        targetValue = -30f,
        animationSpec = tween(100, easing = LinearEasing)
      )

      clockRotation.animateTo(
        targetValue = 0f,
        animationSpec = tween(50, easing = LinearEasing)
      )
    }

    boxAlpha.animateTo(
      targetValue = 0f,
      animationSpec = tween(200, easing = LinearEasing)
    )

    leftWeight.snapTo(0.45f)
    boxAlpha.snapTo(1f)

    rightWeight.animateTo(
      targetValue = 0.01f,
      animationSpec = tween(750, easing = LinearEasing)
    )

    delay(50)

    coroutineScope.launch {
      clockRotation.animateTo(
        targetValue = 30f,
        animationSpec = tween(50, easing = LinearEasing)
      )

      clockRotation.animateTo(
        targetValue = -30f,
        animationSpec = tween(100, easing = LinearEasing)
      )

      clockRotation.animateTo(
        targetValue = 0f,
        animationSpec = tween(50, easing = LinearEasing)
      )
    }

    boxAlpha.animateTo(
      targetValue = 0f,
      animationSpec = tween(200, easing = LinearEasing)
    )

    rightWeight.snapTo(0.45f)
    boxAlpha.snapTo(1f)

    runInactiveAnimation(
      coroutineScope,
      boxAlpha,
      leftWeight,
      rightWeight,
      clockRotation
    )
  }
}
