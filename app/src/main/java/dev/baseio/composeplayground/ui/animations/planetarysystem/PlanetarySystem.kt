package dev.baseio.composeplayground.ui.animations.planetarysystem

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.AnmolVerma
import dev.baseio.composeplayground.ui.animations.pulltorefresh.NightSky
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PlanetarySystem(modifier: Modifier) {

  val height = with(LocalDensity.current) {
    LocalConfiguration.current.screenHeightDp.dp.toPx()
  }

  val universalSunRadius = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(5).dp.toPx()
  }

  val planetRadius = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(25).dp.toPx()
  }

  val planetPosition = with(LocalDensity.current) {
    Offset(
      LocalConfiguration.current.screenWidthDp.div(2).dp.toPx(),
      LocalConfiguration.current.screenHeightDp.div(2).dp.toPx()
    )
  }

  var radian = 0f

  val velocity = 20f / 1000

  val planetX = remember {
    Animatable(0f)
  }

  val planetY = remember {
    Animatable(0f)
  }

  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(true) {
    while (true) {
      radian += velocity
      // Get the new x based on our new angle and radius
      val updatedX = (planetPosition.x + cos(radian.toDouble()) * universalSunRadius).toFloat()
      // Get the new y based on our new angle and radius
      val updatedY = (planetPosition.y + sin(radian.toDouble()) * universalSunRadius).toFloat()

      val jobX = coroutineScope.launch {
        planetX.animateTo(
          updatedX,
        )
      }

      val jobY = coroutineScope.launch {
        planetY.animateTo(
          updatedY,
        )
      }
      joinAll(jobX, jobY)
    }
  }

  Surface(
    modifier
      .background(Color.Black)
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

      Box {
        NightSky(height, particleCount = 250)

        SunBox(universalSunRadius)

        PlanetBox(planetRadius, planetX.value, planetY.value)

        CreatorBlock()
      }
    }
  }
}

@Composable
private fun PlanetBox(planetRadius: Float, planetX: Float, planetY: Float) {
  Canvas(modifier = Modifier
    .fillMaxSize()
    .blur(2.dp), onDraw = {
    planet(planetRadius, Offset(planetX, planetY))
  })
}

@Composable
private fun SunBox(universalSunRadius: Float) {
  Canvas(modifier = Modifier
    .fillMaxSize()
    .blur(2.dp), onDraw = {
    universalSun(universalSunRadius)
  })
}

@Composable
private fun BoxScope.CreatorBlock() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .align(Alignment.BottomCenter)
      .height(200.dp)
      .background(Color.Transparent)
  ) {
    AnmolVerma(Modifier.align(Alignment.Center))
  }
}

private fun DrawScope.universalSun(universalSunRadius: Float) {
  drawCircle(Color(0xfff9d71c), radius = universalSunRadius)
}

private fun DrawScope.planet(planetRadius: Float, offset: Offset) {
  drawCircle(
    color = Color(0xff7da27e),
    radius = planetRadius,
    center = offset
  )
}