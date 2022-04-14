package dev.baseio.composeplayground.ui.animations.adityabhawsar

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.contributors.AdityaBhawsar
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
  var lastState = 0f

  BoxWithConstraints(
    modifier = Modifier
      .fillMaxWidth()
      .height(80.dp)
  ) {
    val swipeableState = rememberSwipeableState(0.5f) { state ->
      if (state == 1f) {
        //Todo  on Swipe End
      } else {
        //Todo on Swipe Start
      }

      lastState = state
      true
    }

    val swipeCenter: Float = (constraints.maxWidth / 2).toFloat() - ((constraints.maxHeight * 1.35f) / 2)
    val swipeRight: Float = constraints.maxWidth - (constraints.maxHeight * 1.35f)
    val swipeLeft = 0f

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

      Text(
        modifier = Modifier.align(Alignment.CenterStart).padding(16.dp),
        text = "Snooze",
        style = TextStyle(
          color = Color.White,
          fontSize = 18.sp
        )
      )

      Text(
        modifier = Modifier.align(Alignment.CenterEnd).padding(16.dp),
        text = "Stop",
        style = TextStyle(
          color = Color.White,
          fontSize = 18.sp
        )
      )

      Box(
        modifier = Modifier
          .fillMaxHeight()
          .aspectRatio(1.35f, true)
          .padding(8.dp)
          .clip(RoundedCornerShape(40.dp))
          .background(Color(0xff626972))
          .align(Alignment.Center)
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
            .size(24.dp)
            .align(Alignment.Center),
          contentDescription = "",
          tint = Color.Black
        )
      }
    }
  }
}
