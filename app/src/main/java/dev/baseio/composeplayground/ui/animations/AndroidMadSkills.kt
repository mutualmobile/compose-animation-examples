package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

val bgColor = Color(20, 47, 65)
val greenAndroidColor = Color(113, 217, 140)
val blueColor = Color(83, 132, 237)

/**
 * https://twitter.com/androiddev/status/1504564052026081286?s=12
 */

@Composable
fun AndroidMadSkills() {
  val widthDP = LocalConfiguration.current.screenWidthDp.dp
  val heightDP = LocalConfiguration.current.screenHeightDp.dp

  val widthPX = with(LocalDensity.current) {
    widthDP.toPx()
  }
  val heightPX = with(LocalDensity.current) {
    heightDP.toPx()
  }

  val ovalSizePx = with(LocalDensity.current) {
    100.dp.toPx()
  }

  val firstOvalX = remember {
    Animatable(0f.minus(ovalSizePx.times(2)))
  }
  val firstOvalY = remember {
    Animatable(heightPX.div(2).minus(ovalSizePx.div(2)))
  }

  val ovalScaleX = remember {
    Animatable(ovalSizePx.times(2))
  }
  val ovalScaleY = remember {
    Animatable(ovalSizePx)
  }

  val cornerRadiusX = remember {
    Animatable(200f)
  }

  val cornerRadiusY = remember {
    Animatable(180f)
  }


  val secondOvalX = remember {
    Animatable(widthPX.plus(ovalSizePx.times(2)))
  }
  val secondOvalY = remember {
    Animatable(heightPX.div(2).minus(ovalSizePx.div(2)))
  }

  val makeMad = remember {
    mutableStateOf(false)
  }

  val makeMadAlpha = remember {
    Animatable(0f)
  }

  // frame 2
  val madTextX = remember {
    Animatable(-widthPX)
  }

  val madTextY = remember {
    Animatable(heightPX.div(2))
  }

  val ltadX = remember {
    Animatable(widthPX.times(0.05f))
  }

  val ltadY = remember {
    Animatable(heightPX.plus(100f))
  }

  val alphaColumn= remember {
    Animatable(0f)
  }

  val madTextScale = remember {
    Animatable(1f)
  }

  val animationScope = rememberCoroutineScope()


  LaunchedEffect(key1 = true, block = {
    firstFrameFirstJob(
      widthPX,
      ovalSizePx,
      animationScope,
      firstOvalX,
      secondOvalX,
      ovalScaleX,
      ovalScaleY, cornerRadiusX, cornerRadiusY
    )
    delay(200)
    firstFrameSecondJob(
      animationScope,
      firstOvalX,
      ovalSizePx,
      secondOvalX,
      widthPX,
      ovalScaleX,
      ovalScaleY, cornerRadiusX, cornerRadiusY
    ) {
      secondFrameJob(
        madTextX,
        madTextY,
        animationScope,
        (widthPX.times(0.05f)),
        widthPX.times(0.2f),
        makeMad,
        madTextScale,
        heightPX.div(4.5f),
        makeMadAlpha,
        ltadY,
        heightPX.div(2.8f),
        alphaColumn
      )
    }

  })


  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(bgColor)
  ) {

    FrameOne(
      firstOvalX,
      firstOvalY,
      cornerRadiusX,
      cornerRadiusY,
      ovalScaleX,
      ovalScaleY,
      secondOvalX,
      secondOvalY
    )

    FrameTwo(
      textX = madTextX,
      textY = madTextY,
      ltadX,
      ltadY,
      madTextScale,
      makeMad.value,
      makeMadAlpha.value,alphaColumn
    )
  }
}

fun secondFrameJob(
  madTextX: Animatable<Float, AnimationVector1D>,
  madTextY: Animatable<Float, AnimationVector1D>,
  animationScope: CoroutineScope,
  endXText: Float,
  towardsCenter: Float,
  makeMad: MutableState<Boolean>,
  madTextScale: Animatable<Float, AnimationVector1D>,
  endY: Float,
  makeMadAlpha: Animatable<Float, AnimationVector1D>,
  ltadY: Animatable<Float, AnimationVector1D>,
  ltadYEnd: Float,
  alphaColumn: Animatable<Float, AnimationVector1D>,
) {
  animationScope.launch {
    makeMadAlpha.animateTo(1f)
    madTextX.animateTo(endXText, tween(durationMillis = 1500))
    delay(200)
    madTextX.animateTo(towardsCenter, tween(durationMillis = 1500))
    delay(200)
    madTextX.animateTo(endXText)
    makeMad.value = true
    animationScope.launch {
      makeMadAlpha.animateTo(0f, tween(1500))
    }
    madTextScale.animateTo(1.2f, tween(1500))
    madTextY.animateTo(endY, tween(1500))
    // we make the text MAD and then append Skills to it.
    delay(200)
    alphaColumn.animateTo(1f, tween(800))
    ltadY.animateTo(ltadYEnd, tween(800))
    delay(400)
    alphaColumn.animateTo(0.4f, tween(800))
  }
}

@Composable
private fun FrameTwo(
  textX: Animatable<Float, AnimationVector1D>,
  textY: Animatable<Float, AnimationVector1D>,
  ltadX: Animatable<Float, AnimationVector1D>,
  ltadY: Animatable<Float, AnimationVector1D>,
  madTextScale: Animatable<Float, AnimationVector1D>,
  makeMad: Boolean,
  makeMadAlpha: Float,
  alphaColumn: Animatable<Float, AnimationVector1D>
) {
  Text(
    text = buildAnnotatedString {
      append(
        AnnotatedString(
          "M",
          spanStyle = SpanStyle(color = greenAndroidColor, fontWeight = FontWeight.Bold)
        )
      )
      append(
        AnnotatedString(
          "odern ",
          spanStyle = SpanStyle(color = blueColor, fontWeight = FontWeight.Bold)
        )
      )

      append(
        AnnotatedString(
          "A",
          spanStyle = SpanStyle(color = greenAndroidColor, fontWeight = FontWeight.Bold)
        )
      )
      append(
        AnnotatedString(
          "ndroid ",
          spanStyle = SpanStyle(color = blueColor, fontWeight = FontWeight.Bold)
        )
      )
      append(
        AnnotatedString(
          "D",
          spanStyle = SpanStyle(color = greenAndroidColor, fontWeight = FontWeight.Bold)
        )
      )
      append(
        AnnotatedString(
          "evelopment ",
          spanStyle = SpanStyle(color = blueColor, fontWeight = FontWeight.Bold)
        )
      )
    },
    style = Typography.h4.copy(fontFamily = FontFamily.Monospace),
    modifier = Modifier
      .offset { IntOffset(x = textX.value.toInt(), y = textY.value.toInt()) }
      .scale(madTextScale.value)
      .alpha(makeMadAlpha)
  )

  AnimatedVisibility(visible = makeMad) {
    Text(
      text = "MAD Skills",
      style = Typography.h4.copy(
        fontFamily = FontFamily.Monospace,
        color = greenAndroidColor,
        fontWeight = FontWeight.Bold
      ),
      modifier = Modifier
        .offset { IntOffset(x = textX.value.toInt(), y = textY.value.toInt()) }
        .scale(madTextScale.value)
    )
  }
  LTADColumn(ltadX.value, ltadY.value,alphaColumn.value)

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LTADColumn(ltadX: Float, ltadY: Float, alpha: Float) {
  Column(Modifier.offset { IntOffset(ltadX.toInt(), ltadY.toInt()) }) {
    ListItem(icon = {
      Icon(
        imageVector = Icons.Default.ShoppingCart,
        contentDescription = null,
        tint = greenAndroidColor
      )
    }, text = {
      Text(
        text = "Language",
        style = Typography.h5.copy(fontWeight = FontWeight.Bold)
      )
    },modifier = Modifier.alpha(alpha))
    Spacer(modifier = Modifier.height(8.dp))
    ListItem(icon = {
      Icon(imageVector = Icons.Default.Face, contentDescription = null, tint = blueColor)
    }, text = {
      Text(text = "Tools", style = Typography.h5.copy(fontWeight = FontWeight.Bold),
        )
    },modifier = Modifier.alpha(alpha))
    Spacer(modifier = Modifier.height(8.dp))
    ListItem(icon = {
      Icon(imageVector = Icons.Default.Home, contentDescription = null, tint = greenAndroidColor)
    }, text = {
      Text(text = "APIs", style = Typography.h5.copy(fontWeight = FontWeight.Bold))
    })
    Spacer(modifier = Modifier.height(8.dp))
    ListItem(icon = {
      Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null, tint = blueColor)
    }, text = {
      Text(text = "Distribution", style = Typography.h5.copy(fontWeight = FontWeight.Bold))
    },modifier = Modifier.alpha(alpha))
  }
}

@Composable
private fun FrameOne(
  firstOvalX: Animatable<Float, AnimationVector1D>,
  firstOvalY: Animatable<Float, AnimationVector1D>,
  cornerRadiusX: Animatable<Float, AnimationVector1D>,
  cornerRadiusY: Animatable<Float, AnimationVector1D>,
  ovalScaleX: Animatable<Float, AnimationVector1D>,
  ovalScaleY: Animatable<Float, AnimationVector1D>,
  secondOvalX: Animatable<Float, AnimationVector1D>,
  secondOvalY: Animatable<Float, AnimationVector1D>
) {
  Oval(
    firstOvalX.value,
    firstOvalY.value,
    cornerRadiusX.value,
    cornerRadiusY.value,
    greenAndroidColor,
    false,
    0f,
    ovalScaleX.value,
    ovalScaleY.value
  )
  Oval(
    secondOvalX.value,
    secondOvalY.value,
    cornerRadiusX.value,
    cornerRadiusY.value,
    blueColor,
    true,
    18f,
    ovalScaleX.value,
    ovalScaleY.value
  )
}

private suspend fun firstFrameFirstJob(
  widthPX: Float,
  ovalSizePx: Float,
  animationScope: CoroutineScope,
  firstOvalX: Animatable<Float, AnimationVector1D>,
  secondOvalX: Animatable<Float, AnimationVector1D>,
  ovalScaleX: Animatable<Float, AnimationVector1D>,
  ovalScaleY: Animatable<Float, AnimationVector1D>,
  cornerRadiusX: Animatable<Float, AnimationVector1D>,
  cornerRadiusY: Animatable<Float, AnimationVector1D>,
) {
  val firstOvalWhenCenter = (widthPX / 2).minus(ovalSizePx)
  val secondOvalWhenCenter = widthPX.minus((widthPX / 2)).plus(ovalSizePx.div(2))

  val firstFrameJob1 = animationScope.launch {
    // bring first oval to center
    firstOvalX.animateTo(
      firstOvalWhenCenter,
      tween(durationMillis = 1500, easing = FastOutLinearInEasing)
    )
  }
  val firstFrameJob2 = animationScope.launch {
    // bring first oval to center
    secondOvalX.animateTo(
      secondOvalWhenCenter,
      tween(durationMillis = 1500, easing = FastOutLinearInEasing)
    )
  }
  val firstFrameJob3 = animationScope.launch {
    // make these ovals as circle
    ovalScaleX.animateTo(ovalSizePx, tween(durationMillis = 1500, easing = FastOutLinearInEasing))
  }
  val firstFrameJob4 = animationScope.launch {
    // make these ovals as circle
    ovalScaleY.animateTo(ovalSizePx, tween(durationMillis = 1500, easing = FastOutLinearInEasing))
  }

  val cornerRadiusXJob = animationScope.launch {
    // make these ovals as circle
    cornerRadiusX.animateTo(200f, tween(durationMillis = 800, easing = FastOutLinearInEasing))
  }
  val cornerRadiusYJob = animationScope.launch {
    // make these ovals as circle
    cornerRadiusY.animateTo(200f, tween(durationMillis = 800, easing = FastOutLinearInEasing))
  }

  joinAll(
    firstFrameJob1,
    firstFrameJob2,
    firstFrameJob3,
    firstFrameJob4,
    cornerRadiusXJob,
    cornerRadiusYJob
  )
}

private suspend fun firstFrameSecondJob(
  animationScope: CoroutineScope,
  firstOvalX: Animatable<Float, AnimationVector1D>,
  ovalSizePx: Float,
  secondOvalX: Animatable<Float, AnimationVector1D>,
  widthPX: Float,
  ovalScaleX: Animatable<Float, AnimationVector1D>,
  ovalScaleY: Animatable<Float, AnimationVector1D>,
  cornerRadiusX: Animatable<Float, AnimationVector1D>,
  cornerRadiusY: Animatable<Float, AnimationVector1D>,
  aboutToFinish: () -> Unit
) {
  animationScope.launch {
    delay(1400)
    aboutToFinish.invoke()
  }
  val secondFrameJob1 = animationScope.launch {
    // bring first oval to center
    firstOvalX.animateTo(
      0f.minus(ovalSizePx.times(4)),
      tween(durationMillis = 1500, easing = FastOutLinearInEasing)
    )
  }
  val secondFrameJob2 = animationScope.launch {
    // bring first oval to center
    secondOvalX.animateTo(
      widthPX.plus(ovalSizePx.times(4)),
      tween(durationMillis = 1500, easing = FastOutLinearInEasing)
    )
  }
  val secondFrameJob3 = animationScope.launch {
    // make these ovals as circle
    ovalScaleX.animateTo(
      ovalSizePx.times(2),
      tween(durationMillis = 800, easing = FastOutLinearInEasing)
    )
  }
  val secondFrameJob4 = animationScope.launch {
    // make these ovals as circle
    ovalScaleY.animateTo(ovalSizePx, tween(durationMillis = 800, easing = FastOutLinearInEasing))
  }

  val cornerRadiusXJob = animationScope.launch {
    // make these ovals as circle
    cornerRadiusX.animateTo(200f, tween(durationMillis = 800, easing = FastOutLinearInEasing))
  }
  val cornerRadiusYJob = animationScope.launch {
    // make these ovals as circle
    cornerRadiusY.animateTo(180f, tween(durationMillis = 800, easing = FastOutLinearInEasing))
  }

  joinAll(
    secondFrameJob1,
    secondFrameJob2,
    secondFrameJob3,
    secondFrameJob4,
    cornerRadiusXJob,
    cornerRadiusYJob
  )
}

@Composable
fun Oval(
  xValue: Float,
  yValue: Float,
  cornerRadiusX: Float,
  cornerRadiusY: Float,
  backgroundColor: Color,
  isBorder: Boolean,
  borderStroke: Float,
  scaleX: Float,
  scaleY: Float
) {

  val width = with(LocalDensity.current) {
    scaleX.toDp()
  }

  val height = with(LocalDensity.current) {
    scaleY.toDp()
  }


  Canvas(modifier = Modifier
    .width(width)
    .height(height)
    .offset { IntOffset(xValue.toInt(), yValue.toInt()) }, onDraw = {
    drawRoundRect(
      backgroundColor,
      cornerRadius = CornerRadius(cornerRadiusX, cornerRadiusY),
      style = if (isBorder) Stroke(borderStroke) else Fill,
    )
  })
}
