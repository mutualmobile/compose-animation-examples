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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.contributors.AdityaBhawsar
import kotlin.math.roundToInt

@Composable
fun AlarmSliderAnimation() {

  Box(modifier = Modifier
    .fillMaxSize()
    .background(Color.Black)){

    Box(modifier = Modifier
      .fillMaxWidth()
      .align(Alignment.Center)){
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
          .background(Color.LightGray))
    }
  }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlarmSlider() {
  var lastState = 0f

  BoxWithConstraints(modifier = Modifier.fillMaxWidth().height(50.dp)) {
    val swipeableState = rememberSwipeableState(0f){ state ->
      if (state == 1f) {
         //Todo  on Swipe End
      } else {
        //Todo on Swipe Start
      }

      lastState = state
      true
    }

    val swipeEnd: Float = constraints.maxWidth - (constraints.maxHeight * 1.5f)
    val anchors = mapOf(
      0f to 0f,
      swipeEnd to 1f
    )

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(Color.White)
        .swipeable(
          state = swipeableState,
          anchors = anchors,
          thresholds = { _, _ -> FractionalThreshold(0.5f) },
          orientation = Orientation.Horizontal
        )
    ){

      Box(
        modifier = Modifier
          .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
          .fillMaxHeight()
          .aspectRatio(1.5f, true)
          .padding(2.dp)
          .shadow(6.dp)
          .background(Color.Yellow)
          .align(Alignment.CenterStart)
          .clip(RoundedCornerShape(8.dp))
      ){
        Icon(
          painter = painterResource(id = R.drawable.ic_mic_white_40),
          modifier = Modifier.size(28.dp).align(Alignment.Center),
          contentDescription = ""
        )
      }

    }
  }
}
