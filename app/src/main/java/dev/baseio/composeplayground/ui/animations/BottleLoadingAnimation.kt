package dev.baseio.composeplayground.ui.animations

import android.graphics.Matrix
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

private val BottleColor = Color(0xffFCCF11)
private const val BottleAnimDuration = 1500
private val LoadingTextStyle
    @Composable get() = TextStyle(
        color = BottleColor,
        fontSize = MaterialTheme.typography.h5.fontSize,
        fontWeight = FontWeight.Bold,
        letterSpacing = 8.sp
    )
private const val BottleAnimEndProgress = 0.77f

@Composable
@Preview
fun BottleLoadingAnimation() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            BottleCloserAnimation()
            repeat(4) { index ->
                SingleBottleLoadingAnimation(delayMillis = index.times(BottleAnimDuration.div(2)))
            }
        }
        LinearProgressBarAnimation()
        Text("LOADING", style = LoadingTextStyle)
    }
}

@Composable
private fun BoxWithConstraintsScope.BottleCloserAnimation() {
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = BottleAnimDuration.div(4), easing = LinearEasing),
            initialStartOffset = StartOffset(
                offsetMillis = BottleAnimDuration.times(2.7f).roundToInt()
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .offset(x = maxWidth.times(0.55f))
            .size(width = 4.dp, height = progress.dp)
            .background(BottleColor)
    ) {}
}

@Composable
private fun LinearProgressBarAnimation() {
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = BottleAnimDuration.times(4),
                easing = LinearEasing
            )
        )
    )
    LinearProgressIndicator(
        progress = progress,
        color = BottleColor,
        modifier = Modifier.padding(vertical = 24.dp)
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoxWithConstraintsScope.SingleBottleLoadingAnimation(delayMillis: Int = 0) {
    var isVisible by remember { mutableStateOf(false) }
    val infiniteTransition = rememberInfiniteTransition()
    val density = LocalDensity.current
    val translationXProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = with(density) { maxWidth.minus(50.dp).toPx() },
        animationSpec = infiniteRepeatable(
            animation = tween(BottleAnimDuration.times(2), easing = LinearEasing),
            initialStartOffset = StartOffset(offsetMillis = delayMillis)
        )
    )
    val animStartLimit = remember { with(density) { maxWidth.times(0.05f).toPx() } }
    val animEndLimit = remember { with(density) { maxWidth.times(0.65f).toPx() } }
    LaunchedEffect(translationXProgress < animStartLimit) {
        if (translationXProgress < animStartLimit) {
            isVisible = true
        }
    }
    LaunchedEffect(translationXProgress > animEndLimit) {
        if (translationXProgress > animEndLimit) {
            isVisible = false
        }
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier.offset(x = with(density) { translationXProgress.toDp() })
    ) { Bottle() }
}

@Composable
private fun Bottle() {
    val bottleShape = GenericShape { size, _ ->
        val w = size.width
        val h = size.height
        val scaleX = w.div(100.0f)
        val scaleY = h.div(100.0f)
        val drawMatrix = Matrix().apply {
            setScale(scaleX, scaleY)
        }
        moveTo(42f, 0f)
        lineTo(39f, 5f)
        lineTo(42f, 5f)
        cubicTo(
            x3 = 30f,
            y3 = 100f,
            x1 = 15f,
            y1 = 70f,
            x2 = 40f,
            y2 = 70f
        )
        quadraticBezierTo(30f, 100f, 70f, 100f)
        cubicTo(
            x3 = 60f,
            y3 = 5f,
            x1 = 60f,
            y1 = 80f,
            x2 = 85f,
            y2 = 70f,
        )
        lineTo(63f, 5f)
        lineTo(59f, 0f)

        asAndroidPath().transform(drawMatrix)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isProgressing by remember { mutableStateOf(false) }
        val progress by animateFloatAsState(
            targetValue = if (isProgressing) BottleAnimEndProgress else 0f,
            tween(durationMillis = BottleAnimDuration, easing = LinearEasing),
        )
        LaunchedEffect(Unit) {
            isProgressing = true
        }
        val bottleCapOffset by animateFloatAsState(targetValue = if (progress == BottleAnimEndProgress) 26f else 8f)
        val bottleCapSize by animateFloatAsState(targetValue = if (progress == BottleAnimEndProgress) 20f else 24f)
        val bottleCapRoundedCorner by animateIntAsState(targetValue = if (progress == BottleAnimEndProgress) 100 else 0)
        Surface(
            modifier = Modifier
                .offset(x = 0.6.dp, y = bottleCapOffset.dp)
                .size(width = bottleCapSize.dp, height = 4.dp),
            shape = RoundedCornerShape(
                topStartPercent = bottleCapRoundedCorner,
                topEndPercent = bottleCapRoundedCorner
            ),
            color = BottleColor
        ) {}
        Card(
            modifier = Modifier
                .fillMaxSize(0.2f)
                .aspectRatio(9f / 12),
            shape = bottleShape,
            elevation = 6.dp,
            border = BorderStroke(color = BottleColor, width = 2.dp)
        ) {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(progress + 0.05f)
                        .background(BottleColor.copy(alpha = 0.5f))
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(progress)
                        .background(BottleColor)
                )
            }
        }
    }
}