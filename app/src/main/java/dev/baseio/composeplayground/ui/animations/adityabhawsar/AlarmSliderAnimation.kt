package dev.baseio.composeplayground.ui.animations.adityabhawsar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.contributors.AdityaBhawsar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    val swipeableState = rememberSwipeableState(0.5f) { true }

    val swipeCenter: Float = (constraints.maxWidth / 2).toFloat() - ((constraints.maxHeight * 1.35f) / 2)
    val swipeRight: Float = constraints.maxWidth - (constraints.maxHeight * 1.35f)
    val swipeLeft = 0f

    val anchors = mapOf(
      swipeLeft to 0f,
      swipeCenter to 0.5f,
      swipeRight to 1f
    )

    val boxAlpha = remember { Animatable(1f) }
    val leftWeight = remember { Animatable(0.45f) }
    val rightWeight = remember { Animatable(0.45f) }
    val clockRotation = remember { Animatable(0f) }

    LaunchedEffect(key1 = swipeableState.currentValue == 0.5f) {
      runInactiveAnimation(
        this,
        boxAlpha,
        leftWeight,
        rightWeight,
        clockRotation
      )
    }

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
            .size(24.dp)
            .align(Alignment.Center),
          contentDescription = "",
          tint = Color.Black
        )
      }
    }
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
