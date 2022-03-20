package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import dev.baseio.composeplayground.contributors.AnmolVerma
import dev.baseio.composeplayground.ui.animations.netflixanim.EffectBrush
import dev.baseio.composeplayground.ui.animations.netflixanim.EffectLumieres
import kotlinx.coroutines.launch

val baseColor = Color(0xffe40913)

/**
 * Inspiration
 * https://dev.to/claudiobonfati/netflix-intro-animation-pure-css-1m0c
 */
@Composable
fun NetflixIntroAnimation() {

  // body
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {
    //container
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    )
    {
      //netflix intro
      NetflixIntro(Modifier)
    }


    Box(
      modifier = Modifier
        .align(Alignment.BottomEnd)
    ) {
      AnmolVerma(
        Modifier
          .padding(24.dp)
          .align(Alignment.Center)
      )
    }

  }
}

@Composable
fun NetflixIntro(modifier: Modifier) {
  val zoomInNetflixBox = remember {
    Animatable(1f)
  }

  val fadingLumieresBox = remember {
    Animatable(baseColor.copy(alpha = 0.5f))
  }


  val showingLumieres = remember {
    Animatable(1f)
  }

  val brushMovingHelper1 = remember {
    Animatable(0f)
  }

  val brushMovingHelper3 = remember {
    Animatable(0f)
  }

  val brushMovingHelper2 = remember {
    Animatable(0f)
  }

  val fadingOut by animateFloatAsState(targetValue = 1f, animationSpec = keyframes {
    durationMillis = 2500
    delayMillis = 1200
    1f at 0 with LinearEasing
    0f at 2500 with LinearEasing
  })



  LaunchedEffect(key1 = true, block = {
    launch {
      zoomInNetflixBox.animateTo(15f, animationSpec = keyframes {
        durationMillis = 3500
        delayMillis = 500
        1f at 0 with LinearEasing
        15f at 3500 with LinearEasing
      })
    }
    launch {
      fadingLumieresBox.animateTo(
        targetValue = baseColor.copy(alpha = 0f),
        animationSpec = keyframes {
          durationMillis = 2000
          delayMillis = 600
          baseColor.copy(alpha = 0.5f) at 0 with LinearEasing
          baseColor.copy(alpha = 0f) at 2500 with LinearEasing
        })
    }
    launch {
      brushMovingHelper1.animateTo(targetValue = -100f, animationSpec = keyframes {
        durationMillis = 2500
        delayMillis = 1200
        0f at 0 with LinearEasing
        -100f at 2500 with LinearEasing
      })
    }

    launch {
      brushMovingHelper3.animateTo(targetValue = -100f, animationSpec = keyframes {
        durationMillis = 2000
        delayMillis = 800
        0f at 0 with LinearEasing
        -100f at 2000 with LinearEasing
      })
    }

    launch {
      brushMovingHelper2.animateTo(targetValue = -100f, animationSpec = keyframes {
        durationMillis = 2000
        delayMillis = 500
        0f at 0 with LinearEasing
        -100f at 2000 with LinearEasing
      })
    }

    launch {
      showingLumieres.animateTo(1f, keyframes {
        durationMillis = 2000
        delayMillis = 1600
        0f at 0 with LinearEasing
        1f at 2500 with LinearEasing
      })
    }
  })

  // netflix intro

  val width = with(LocalDensity.current) {
    300f.toDp()
  }

  val height = with(LocalDensity.current) {
    300f.toDp()
  }

  //letter N
  Box(
    modifier = modifier
      .width(width)
      .height(height)
      .graphicsLayer(
       scaleX = zoomInNetflixBox.value, scaleY = zoomInNetflixBox.value,
        transformOrigin = TransformOrigin.Center.copy(
          pivotFractionX = 0.5f,
          pivotFractionY = 0.3f
        )
      )
  ) {
    val helperOneWidth = 19.5f.div(100).times(width)
    val helperOneHeight = 1.times(height)
    HelperOne(
      modifier = Modifier
        .width(helperOneWidth)
        .fillMaxHeight()
        .offset(x = (22.4 / 100).times(width), y = 0.dp)
        .rotate(180f)
        .background(fadingLumieresBox.value)
        .shadow(elevation = 4.dp), brushMovingHelper1, showingLumieres, helperOneWidth,helperOneHeight
    )
    val helperTwoWidth = 19f.div(100).times(width)

    HelperTwo(
      modifier = Modifier
        .fillMaxWidth(0.19f)
        .fillMaxHeight()
        .offset(x = (57.8 / 100).times(width), y = 0.dp)
        .rotate(180f)
        .background(fadingLumieresBox.value)
        .shadow(elevation = 4.dp), brushMovingHelper2, helperTwoWidth,helperOneHeight
    )
    val helperThreeHeight = 1.5.times(height)

    HelperThree(
      modifier = Modifier
        .fillMaxWidth(0.19f)
        .fillMaxHeight(1.5f)
        .offset(x = (40.5 / 100).times(width), y = (-25 / 100).times(height))
        .rotate(-19.5f)
        .background(fadingLumieresBox.value)
        .shadow(elevation = 4.dp), brushMovingHelper3, helperTwoWidth,helperThreeHeight
    )
  }


}

@Composable
fun HelperTwo(
  modifier: Modifier,
  brushMovingHelper3: Animatable<Float, AnimationVector1D>,
  helperTwoWidth: Dp,
  helperOneHeight: Dp
) {
  Box(modifier = modifier) {
    EffectBrush(brushMovingHelper3, helperTwoWidth,helperOneHeight)
  }
}


@Composable
fun HelperThree(
  modifier: Modifier,
  brushMoving: Animatable<Float, AnimationVector1D>,
  helperTwoWidth: Dp,
  helperThreeHeight: Dp
) {
  Box(modifier = modifier) {
    EffectBrush(brushMoving, helperTwoWidth,helperThreeHeight)
  }
}

@Composable
fun HelperOne(
  modifier: Modifier = Modifier,
  brushMoving: Animatable<Float, AnimationVector1D>,
  showingLumieres: Animatable<Float, AnimationVector1D>,
  helperOneWidth: Dp,
  helperOneHeight: Dp
) {
  Box(modifier = modifier) {
    EffectBrush(brushMoving, helperOneWidth, helperOneHeight)
    EffectLumieres(showingLumieres, helperOneWidth)
  }
}


private fun DrawScope.drawN(
  netflixHeight: Float,
  colorFirstN: Float,
  colorMiddleN: Float,
  colorSecondN: Float
) {
  // draw N
  // First Draw last |
  var initialX = 0f
  val lineSpacing = 1f
  val totalLines = 75
  val nRotation = -25f

  val centerOfNRotation = center.copy(
    x = center.x.plus(totalLines.times(1.5f)),
    y = lineSpacing.times(totalLines.times(2.5f))
  )

  repeat(totalLines) {
    drawLine(
      brush = Brush.verticalGradient(
        listOf(
          Color.Black,
          baseColor.copy(alpha = colorFirstN),
        ), startY = 0f, endY = netflixHeight.times(colorFirstN)
      ),
      start = Offset(initialX, 0f),
      end = Offset(initialX, netflixHeight.times(colorFirstN))
    )
    initialX += lineSpacing
  }


  // Now Draw middle \
  var initialOfMiddle = initialX
  rotate(
    nRotation,
    centerOfNRotation
  ) {
    repeat(totalLines) {
      drawLine(
        brush = Brush.verticalGradient(
          listOf(
            Color.Black,
            baseColor.copy(alpha = colorMiddleN),
          ), startY = 0f, endY = netflixHeight.times(colorFirstN)
        ),
        start = Offset(initialOfMiddle, 0f),
        end = Offset(initialOfMiddle, netflixHeight.times(colorFirstN))
      )
      initialOfMiddle += lineSpacing
    }
  }

  var initialOfFirst = initialOfMiddle
  repeat(totalLines) {
    drawLine(
      brush = Brush.verticalGradient(
        listOf(
          Color.Black,
          baseColor.copy(alpha = colorSecondN),
        ), startY = 0f, endY = netflixHeight.times(colorFirstN)
      ),
      start = Offset(initialOfFirst, 0f),
      end = Offset(initialOfFirst, netflixHeight.times(colorFirstN))
    )
    initialOfFirst += lineSpacing
  }
}
