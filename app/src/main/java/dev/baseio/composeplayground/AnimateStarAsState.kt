package dev.baseio.composeplayground

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.ui.animations.pulltorefresh.NightSky

@Composable
fun AnimateStarAsState() {

  val centerX =
    LocalDensity.current.run { LocalConfiguration.current.screenWidthDp.dp.toPx().div(2) }
  val centerY =
    LocalDensity.current.run { LocalConfiguration.current.screenHeightDp.dp.toPx().div(2) }

  val infiniteTransition = rememberInfiniteTransition()

  val rotationY by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = InfiniteRepeatableSpec(keyframes {
      durationMillis = 5000
      0f at 0 with LinearEasing
      30f at 2500 with LinearEasing
      45f at 3500 with LinearEasing
      65f at 5000 with LinearEasing
    }, repeatMode = RepeatMode.Restart)
  )

  val rotationZ by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = InfiniteRepeatableSpec(keyframes {
      durationMillis = 5000
      0f at 0 with LinearEasing
      -10f at 2500 with LinearEasing
      -12f at 3500 with LinearEasing
      -18f at 5000 with LinearEasing
    }, repeatMode = RepeatMode.Restart)
  )

  val airplaneScale by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = InfiniteRepeatableSpec(keyframes {
      durationMillis = 5000
      1.5f at 0 with LinearEasing
      1f at 2500 with LinearEasing
      0.8f at 5000 with LinearEasing
    }, repeatMode = RepeatMode.Restart)
  )

  val airplaneX by infiniteTransition.animateFloat(0f, 1f, InfiniteRepeatableSpec(keyframes {
    durationMillis = 5000
    0f at 0 with LinearEasing
    centerX.div(4) at 0 with LinearEasing
    centerX.div(2) at 2500 with LinearEasing
    centerX.times(2.5f) at 5000 with LinearEasing
  }, repeatMode = RepeatMode.Restart))

  val airplaneY by infiniteTransition.animateFloat(0f, 1f, InfiniteRepeatableSpec(keyframes {
    durationMillis = 5000
    centerY at 0 with LinearEasing
    centerY.times(0.95f) at 1500 with LinearEasing
    centerY.times(0.8f) at 2500 with LinearEasing
    centerY.times(0.5f) at 3800 with LinearEasing
    centerY.times(0.0f) at 5000 with LinearEasing
  }, repeatMode = RepeatMode.Restart))


  Box(modifier = Modifier.fillMaxSize()) {

    NightSky(height = centerY.times(2))
    Image(
      painter = painterResource(id = R.drawable.airplane),
      contentDescription = null, modifier = Modifier
        .offset {
          IntOffset(
            airplaneX
              .toInt(),
            airplaneY
              .toInt()
          )
        }
        .scale(airplaneScale)
        .graphicsLayer(rotationY = rotationY, rotationZ = rotationZ)
    )

  }


}