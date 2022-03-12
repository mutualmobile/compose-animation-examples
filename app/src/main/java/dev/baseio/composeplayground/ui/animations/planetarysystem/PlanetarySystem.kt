package dev.baseio.composeplayground.ui.animations.planetarysystem

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
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.AnmolVerma
import dev.baseio.composeplayground.ui.animations.pulltorefresh.NightSky

@Composable
fun PlanetarySystem(modifier: Modifier) {

  val height = with(LocalDensity.current) {
    LocalConfiguration.current.screenHeightDp.dp.toPx()
  }

  val centerOffset = with(LocalDensity.current) {
    Offset(
      LocalConfiguration.current.screenWidthDp.div(2).dp.toPx(),
      LocalConfiguration.current.screenHeightDp.div(2).dp.toPx()
    )
  }


  val solarSystem by remember {
    mutableStateOf(SolarSystem(centerOffset))
  }


  Surface(
    modifier
      .background(Color.Black)
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

      Box {
        NightSky(height, particleCount = 2500)

        Canvas(modifier = Modifier
          .fillMaxSize(), onDraw = {
          this.drawIntoCanvas {
            solarSystem.animate(it)
          }
        })
        CreatorBlock()
      }
    }
  }
}

@Composable
private fun PlanetBox(planetRadius: Float, planetX: Float, planetY: Float, color: Color) {

  Canvas(modifier = Modifier
    .fillMaxSize()
    .blur(2.dp), onDraw = {
    val offset = Offset(planetX, planetY)
    drawCircle(
      color = color,
      radius = planetRadius,
      center = offset
    )
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