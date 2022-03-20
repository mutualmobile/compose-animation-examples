package dev.baseio.composeplayground.ui.animations.netflixanim

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun EffectLumieres(
  showingLumieres: Animatable<Float, AnimationVector1D>,
) {

  val coroutineScope = rememberCoroutineScope()

  val width = LocalConfiguration.current.screenWidthDp

  val lamps by remember {
    mutableStateOf(theLamps)
  }

  LaunchedEffect(key1 = true, block = {
    lamps.forEachIndexed { index, lamp ->
      val animName = if (index % 2 == 0) LUMIERE_RIGHT else LUMIERE_LEFT

      if (animName == LUMIERE_LEFT) {
        coroutineScope.launch {
          lamp.lumiereMovingTranslate.animateTo(120f, keyframes {
            durationMillis = 2500
            delayMillis = lamp.animDelay.toInt()
            0f at 0 with LinearEasing
            10f at 1000 with LinearEasing
            60f at 1250 with LinearEasing
            120f at 2500 with LinearEasing
          })
        }
        coroutineScope.launch {
          lamp.lumiereMovingScale.animateTo(3f, keyframes {
            durationMillis = 2500
            delayMillis = lamp.animDelay.toInt()
            1f at 1000 with LinearEasing
            3f at 2500 with LinearEasing
          })
        }
      } else {
        coroutineScope.launch {
          lamp.lumiereMovingTranslate.animateTo(-120f, keyframes {
            durationMillis = 2500
            delayMillis = lamp.animDelay.toInt()
            0f at 0 with LinearEasing
            -10f at 1000 with LinearEasing
            -60f at 1250 with LinearEasing
            -120f at 2500 with LinearEasing
          })
        }
        coroutineScope.launch {
          lamp.lumiereMovingScale.animateTo(3f, keyframes {
            durationMillis = 2500
            delayMillis = lamp.animDelay.toInt()
            1f at 1000 with LinearEasing
            3f at 2500 with LinearEasing
          })
        }
      }
    }
  })


  Box(
    modifier = Modifier
      .fillMaxSize()
      .graphicsLayer(alpha = showingLumieres.value)
  ) {

    repeat(lamps.size) {
      val lamp = lamps[it]
      val animName = if (it % 2 == 0) LUMIERE_RIGHT else LUMIERE_LEFT
      val offsetX = LocalDensity.current.run {
        width.times(lamp.left.div(100).toDp())
      }
      LampComposable(
        Modifier
          .width(LocalDensity.current.run { lamp.width.toDp() })
          .fillMaxHeight()
          .zIndex(lamp.z)
          .background(lamp.color)
          .offset(x = offsetX), animName, lamp, coroutineScope
      )
    }

  }
}

const val LUMIERE_LEFT = "left"
const val LUMIERE_RIGHT = "right"

@Composable
fun LampComposable(
  modifier: Modifier,
  animName: String,
  lamp: Lamp,
  coroutineScope: CoroutineScope
) {
  Box(
    modifier = modifier
      .graphicsLayer(
        translationX = lamp.lumiereMovingTranslate.value,
        translationY = lamp.lumiereMovingTranslate.value,
        scaleX = lamp.lumiereMovingScale.value,
        scaleY = lamp.lumiereMovingScale.value
      )
  )
}

val random = Random(500)

val theLamps: List<Lamp> = mutableListOf<Lamp>().apply {
  add(
    Lamp(
      Color(0xffff0100),
      z = 6f,
      left = 0.7f,
      width = 1f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffffde01),
      left = 2.2f,
      width = 1.4f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffff00cc),
      left = 5.8f,
      width = 2.1f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xff04fd8f),
      left = 10.1f,
      width = 2f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffff0100),
      left = 12.9f,
      width = 1.4f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffff9600),
      left = 15.3f,
      width = 2.8f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xff0084ff),
      left = 21.2f,
      width = 2.5f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xfff84006),
      left = 25f,
      width = 2.5f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffffc601),
      left = 30.5f,
      width = 3f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffff4800),
      left = 36.3f,
      width = 3f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xfffd0100),
      left = 41f,
      width = 2.2f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xff01ffff),
      left = 44.2f,
      width = 2.6f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffffc601),
      left = 51.7f,
      width = 0.5f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffffc601),
      z = 1f,
      left = 52.1f,
      width = 1.8f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xff0078fe),
      z = 1f,
      left = 53.5f,
      width = 2.3f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xff0080ff),
      z = 1f,
      left = 57.2f,
      width = 2f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffffae01),
      z = 1f,
      left = 62.3f,
      width = 2.9f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffff00bf),
      z = 1f,
      left = 65.8f,
      width = 1.7f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffa601f4),
      z = 1f,
      left = 72.8f,
      width = 0.8f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xfff30b34),
      z = 1f,
      left = 74.3f,
      width = 2f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffff00bf),
      z = 1f,
      left = 79.8f,
      width = 2f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xff04fd8f),
      z = 1f,
      left = 78.2f,
      width = 2f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xff01ffff),
      z = 1f,
      left = 78.5f,
      width = 2f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffa201ff),
      z = 1f,
      left = 85.3f,
      width = 1.1f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffec0014),
      z = 1f,
      left = 86.9f,
      width = 1.1f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xff0078fe),
      z = 1f,
      left = 88.8f,
      width = 2f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xffff0036),
      z = 1f,
      left = 92.4f,
      width = 2.4f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )
  add(
    Lamp(
      Color(0xff06f98c),
      z = 1f,
      left = 96.2f,
      width = 2.1f,
      animDelay = (random.nextFloat().div(100)) + size
    )
  )

}

data class Lamp(
  val color: Color,
  val z: Float = 1f,
  val left: Float,
  val width: Float,
  val animDelay: Float
) {
  val lumiereMovingTranslate = Animatable(0f)

  val lumiereMovingScale = Animatable(1f)
}
