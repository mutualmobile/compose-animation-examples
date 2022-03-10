package dev.baseio.composeplayground.ui.animations.planetarysystem

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
import dev.baseio.composeplayground.ui.animations.pulltorefresh.NightSky
import kotlin.math.min
import kotlin.random.Random


/**
 * https://codepen.io/leimapapa/pen/wvPLbBd
 */
@Composable
fun PlanetarySystem(modifier: Modifier, numOfPlanets: Int = 9) {
  val height = with(LocalDensity.current) {
    LocalConfiguration.current.screenHeightDp.dp.toPx()
  }

  val universalSunRadius = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(5).dp.toPx()
  }

  val planetRadius = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.div(10).dp.toPx()
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
          repeat(numOfPlanets) {
            planet(it+1, planetRadius)
          }
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

private fun DrawScope.planet(index: Int, planetRadius: Float) {
  val color = (Math.random() * 16777215).toInt() or (0xFF shl 24)

  val posX =
    min(random.nextDouble((center.x / 1.5), center.x / 1.2).toFloat(), center.x)
  val posY =
    min(random.nextDouble((center.y / 1.8), center.y / 1.6).toFloat(), center.y)

  val x = index.times(posX)
  val y = index.times(posY)

  drawCircle(
    color = Color(color),
    radius = planetRadius,
    center = Offset(x, y)
  )
}

val random = Random
