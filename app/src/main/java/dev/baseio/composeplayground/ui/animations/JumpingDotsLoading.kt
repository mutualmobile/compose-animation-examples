package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.coloredShadow

private val Color1 = Color(0xffFE9305)
private val Color2 = Color(0xffFF0482)
private const val AnimDurationMillis = 2000
private val Animation = tween<Float>(durationMillis = AnimDurationMillis)
private val ColorAnimation = tween<Color>(durationMillis = AnimDurationMillis)
private val AnimationSpec = infiniteRepeatable(
    animation = Animation,
    repeatMode = RepeatMode.Reverse
)
private val ColorAnimationSpec = infiniteRepeatable(
    animation = ColorAnimation,
    repeatMode = RepeatMode.Reverse
)
private const val CircleSize = 40
private const val CirclePadding = 10
private const val CircleSizeWithPadding = CircleSize + CirclePadding
private const val NumberOfCircles = 5
private const val CircleShadowAlpha = 0.5f
private const val CircleShadowRadius = 8
private const val CircleShadowBorderRadius = 32
private const val CircleShadowOffset = 4

@Preview
@Composable
fun JumpingDotsLoadingAnimation() {
    BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val density = LocalDensity.current
        val transition = rememberInfiniteTransition()

        val firstCirclePathAnim by transition.animateFloat(
            initialValue = 0f,
            targetValue = with(density) {
                CircleSizeWithPadding.dp
                    .times(NumberOfCircles)
                    .minus(CirclePadding.dp)
                    .toPx()
            },
            animationSpec = AnimationSpec
        )
        val firstCircleColorAnim by transition.animateColor(
            initialValue = Color1,
            targetValue = Color2,
            animationSpec = ColorAnimationSpec
        )
        val secondCircleXPath by animateDpAsState(
            targetValue = if (firstCircleColorAnim == Color1) 0.dp else 1.dp,
            keyframes {
                durationMillis = AnimDurationMillis.times(2)
                0.dp at 0 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 700 with LinearOutSlowInEasing
                (-CircleSizeWithPadding).dp at 3400 with FastOutSlowInEasing
                0.dp at AnimDurationMillis.times(2) with FastOutSlowInEasing
            })
        val secondCircleYPath by animateDpAsState(
            targetValue = if (firstCircleColorAnim == Color1) 0.dp else 1.dp,
            keyframes {
                durationMillis = AnimDurationMillis.times(2)
                0.dp at 0 with FastOutSlowInEasing
                CircleSizeWithPadding.dp at 300 with FastOutSlowInEasing
                0.dp at 600 with FastOutSlowInEasing
                0.dp at 3300 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 3600 with FastOutSlowInEasing
                0.dp at AnimDurationMillis.times(2) with FastOutSlowInEasing
            })
        val thirdCircleXPath by animateDpAsState(
            targetValue = if (firstCircleColorAnim == Color1) 0.dp else 1.dp,
            keyframes {
                durationMillis = AnimDurationMillis.times(2)
                0.dp at 0 with FastOutSlowInEasing
                0.dp at 300 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 1000 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 3000 with FastOutSlowInEasing
                0.dp at AnimDurationMillis.times(2) with FastOutSlowInEasing
            })
        val thirdCircleYPath by animateDpAsState(
            targetValue = if (firstCircleColorAnim == Color1) 0.dp else 1.dp,
            keyframes {
                durationMillis = AnimDurationMillis.times(2)
                0.dp at 0 with FastOutSlowInEasing
                0.dp at 300 with FastOutSlowInEasing
                CircleSizeWithPadding.dp at 600 with FastOutSlowInEasing
                0.dp at 1000 with FastOutSlowInEasing
                0.dp at 3000 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 3500 with FastOutSlowInEasing
                0.dp at 3800 with FastOutSlowInEasing
            })
        val fourthCircleXPath by animateDpAsState(
            targetValue = if (firstCircleColorAnim == Color1) 0.dp else 1.dp,
            keyframes {
                durationMillis = AnimDurationMillis.times(2)
                0.dp at 0 with FastOutSlowInEasing
                0.dp at 500 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 1500 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 2500 with FastOutSlowInEasing
                0.dp at AnimDurationMillis.times(2) with FastOutSlowInEasing
            })
        val fourthCircleYPath by animateDpAsState(
            targetValue = if (firstCircleColorAnim == Color1) 0.dp else 1.dp,
            keyframes {
                durationMillis = AnimDurationMillis.times(2)
                0.dp at 0 with FastOutSlowInEasing
                0.dp at 500 with FastOutSlowInEasing
                CircleSizeWithPadding.dp at 900 with FastOutSlowInEasing
                0.dp at 1400 with FastOutSlowInEasing
                0.dp at 2700 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 3100 with FastOutSlowInEasing
                0.dp at 3500 with FastOutSlowInEasing
            })
        val fifthCircleXPath by animateDpAsState(
            targetValue = if (firstCircleColorAnim == Color1) 0.dp else 1.dp,
            keyframes {
                durationMillis = AnimDurationMillis.times(2)
                0.dp at 0 with FastOutSlowInEasing
                0.dp at 500 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 1900 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 2000 with FastOutSlowInEasing
                0.dp at AnimDurationMillis.times(2) with FastOutSlowInEasing
            })
        val fifthCircleYPath by animateDpAsState(
            targetValue = if (firstCircleColorAnim == Color1) 0.dp else 1.dp,
            keyframes {
                durationMillis = AnimDurationMillis.times(2)
                0.dp at 0 with FastOutSlowInEasing
                0.dp at 500 with FastOutSlowInEasing
                CircleSizeWithPadding.dp at 1200 with FastOutSlowInEasing
                0.dp at 1900 with FastOutSlowInEasing
                0.dp at 2100 with FastOutSlowInEasing
                (-CircleSizeWithPadding).dp at 2700 with FastOutSlowInEasing
                0.dp at 3400 with FastOutSlowInEasing
            })
        Row {
            ColoredCircle(
                color = firstCircleColorAnim,
                modifier = Modifier.offset(x = with(density) { firstCirclePathAnim.toDp() })
            )
            ColoredCircle(
                color = Color1,
                modifier = Modifier.offset(x = secondCircleXPath, y = secondCircleYPath)
            )
            ColoredCircle(
                color = Color1,
                modifier = Modifier.offset(x = thirdCircleXPath, y = thirdCircleYPath)
            )
            ColoredCircle(
                color = Color1,
                modifier = Modifier.offset(x = fourthCircleXPath, y = fourthCircleYPath)
            )
            ColoredCircle(
                color = Color1,
                modifier = Modifier.offset(x = fifthCircleXPath, y = fifthCircleYPath)
            )
        }
    }
}

@Composable
fun ColoredCircle(modifier: Modifier = Modifier, color: Color) {
    Card(
        shape = CircleShape,
        modifier = modifier
            .padding(CirclePadding.dp)
            .size(CircleSize.dp)
            .coloredShadow(
                color = color,
                alpha = CircleShadowAlpha,
                shadowRadius = CircleShadowRadius.dp,
                borderRadius = CircleShadowBorderRadius.dp,
                offsetX = CircleShadowOffset.dp,
                offsetY = CircleShadowOffset.dp,
            ),
        backgroundColor = color,
    ) {}
}