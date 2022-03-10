package dev.baseio.composeplayground.ui.animations.planetarysystem

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.AnmolVerma
import dev.baseio.composeplayground.ui.animations.NotificationBellDefinition
import dev.baseio.composeplayground.ui.animations.isBellMoveMode
import dev.baseio.composeplayground.ui.animations.pulltorefresh.NightSky

/**
 * Inspiration
 * https://codepen.io/leimapapa/pen/wvPLbBd
 */
@Composable
fun PlanetarySystem(modifier: Modifier) {

  var needsAnimate by remember {
    mutableStateOf(false)
  }

  val height = with(LocalDensity.current) {
    LocalConfiguration.current.screenHeightDp.dp.toPx()
  }

  val universalSunRadius = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(5).dp.toPx()
  }

  val planetRadius = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(10).dp.toPx()
  }

  val centerOffset = with(LocalDensity.current) {
    Offset(
      LocalConfiguration.current.screenWidthDp.div(2).dp.toPx(),
      LocalConfiguration.current.screenHeightDp.div(2).dp.toPx()
    )
  }


  val planetX by animateFloatAsState(
    targetValue = if (needsAnimate) 1f else 0f,
    animationSpec = infiniteRepeatable(keyframes {
      durationMillis = 1000
      centerOffset.x at 0 with FastOutLinearInEasing
      centerOffset.x.plus(10f) at 250 with FastOutLinearInEasing
      centerOffset.x.plus(20f) at 500 with FastOutLinearInEasing
      centerOffset.x.plus(30f) at 750 with FastOutLinearInEasing
      centerOffset.x.plus(40f) at 1000 with FastOutLinearInEasing
    })
  )

  val planetY by animateFloatAsState(
    targetValue = if (needsAnimate) 1f else 0f,
    animationSpec = infiniteRepeatable(keyframes {
      durationMillis = 1000
      centerOffset.y at 0 with FastOutLinearInEasing
      centerOffset.y.plus(10f) at 250 with FastOutLinearInEasing
      centerOffset.y.plus(20f) at 500 with FastOutLinearInEasing
      centerOffset.y.plus(30f) at 750 with FastOutLinearInEasing
      centerOffset.y.plus(40f) at 1000 with FastOutLinearInEasing
    })
  )


  LaunchedEffect(true) {
    needsAnimate = !needsAnimate
  }


  Surface(
    modifier
      .background(Color.Black)
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

      Box {
        NightSky(height, particleCount = 500)

        Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
          universalSun(universalSunRadius)
          planet(planetRadius, Offset(planetX, planetY))
        })

        CreatorBlock()

      }
    }
  }
}

@Composable
private fun BoxScope.CreatorBlock() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .align(Alignment.BottomCenter)
      .height(200.dp)
      .padding(bottom = 8.dp)
      .background(MaterialTheme.colors.background)
  ) {
    AnmolVerma(Modifier.align(Alignment.Center))
  }
}

private fun DrawScope.universalSun(universalSunRadius: Float) {
  drawCircle(Color.Yellow, radius = universalSunRadius)
}

private fun DrawScope.planet(planetRadius: Float, offset: Offset) {
  drawCircle(
    color = Color.Green,
    radius = planetRadius,
    center = offset
  )
}