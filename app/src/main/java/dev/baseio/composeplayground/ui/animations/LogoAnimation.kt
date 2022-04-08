package dev.baseio.composeplayground.ui.animations

import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R
import kotlin.math.roundToInt

private const val duration = 1000
private const val yDuration = duration.div(2)
private val StreamIconColor = Color(0xff1461FF)

@Preview
@Composable
fun LogoAnimation(
    @DrawableRes logo: Int = R.drawable.ic_streamlogo,
) {
    val ctx = LocalContext.current
    val transition = rememberInfiniteTransition()
    val rotation by transition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration),
            repeatMode = RepeatMode.Reverse
        )
    )
    val translationYAnim by transition.animateFloat(
        initialValue = -10f,
        targetValue = 40f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = yDuration),
            repeatMode = RepeatMode.Reverse
        )
    )
    val translationXAnim by transition.animateFloat(
        initialValue = -50f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .aspectRatio(1f),
            shape = CircleShape,
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        ) {
            Box(contentAlignment = Alignment.TopCenter) {
                val wave1Src by remember {
                    mutableStateOf(
                        BitmapFactory.decodeResource(
                            ctx.resources,
                            R.drawable.stream_wave
                        )
                    )
                }
                val wave1 = wave1Src.asImageBitmap()
                val wave1Offset by transition.animateFloat(
                    initialValue = wave1.width.div(3.4f),
                    targetValue = wave1.width.div(1.5f),
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = duration.times(9),
                            easing = LinearEasing
                        ),
                    )
                )
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .align(Alignment.BottomCenter)
                ) {
                    drawImage(
                        image = wave1,
                        srcOffset = IntOffset(x = wave1Offset.roundToInt(), y = 0)
                    )
                }
                Image(
                    painter = painterResource(id = logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .rotate(rotation)
                        .graphicsLayer {
                            translationX = translationXAnim
                            translationY = translationYAnim
                        },
                    colorFilter = ColorFilter.tint(color = StreamIconColor)
                )
            }
        }
    }
}