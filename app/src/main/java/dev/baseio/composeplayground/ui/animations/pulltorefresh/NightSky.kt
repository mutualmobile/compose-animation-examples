package dev.baseio.composeplayground.ui.animations.pulltorefresh

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.ui.animations.pulltorefresh.particlesystem.StarParticleSystem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Sky(content: @Composable (BoxScope) -> Unit) {
  val height = with(LocalDensity.current) {
    200.dp.toPx()
  }
  Box(
    Modifier
      .fillMaxWidth()
      .height(200.dp)
  ) {
    Box {
      if (isSystemInDarkTheme()) {
        NightSky(height)
      } else {
        DaySky()
      }
      content(this)
    }

  }
}

@Composable
fun DaySky() {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xff1fb4ff))
  ) {
    Canvas(
      modifier = Modifier.fillMaxSize()
    ) {

    }
  }
}

@Composable
fun NightSky(height: Float, particleCount: Int = 100) {
  val width = with(LocalDensity.current) {
    LocalConfiguration.current.screenWidthDp.dp.toPx()
  }

  val nightParticles by remember {
    mutableStateOf(StarParticleSystem(width, height, particleCount))
  }
  val coroutineScope = rememberCoroutineScope()


  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {
    Canvas(modifier = Modifier.fillMaxSize()) {
      nightParticles.particles.forEach {
        drawCircle(Color.White, it.scale, it.pos.toOffset(), it.alpha)
      }
      coroutineScope.launch {
        nightParticles.update()
      }
    }
  }
}

private fun PointF.toOffset(): Offset {
  return Offset(this.x, this.y)
}
