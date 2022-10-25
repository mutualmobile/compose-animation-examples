package dev.baseio.composeplayground.ui.animations

import android.graphics.Matrix
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.contributors.ShubhamSingh

@Composable
fun BladeAnimation() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            val density = LocalDensity.current
            val animDuration = remember { 4000 }
            val infiniteTransition = rememberInfiniteTransition()
            val animatedScale by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 0.8f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = animDuration
                        1f at durationMillis.times(0) with FastOutSlowInEasing
                        0.8f at durationMillis.times(0.5f).toInt() with FastOutSlowInEasing
                        1f at durationMillis
                    }
                )
            )
            val animatedBladeScale by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = animDuration
                        0f at durationMillis.times(0)
                        0f at durationMillis.times(0.6f).toInt() with FastOutSlowInEasing
                        1f at durationMillis.times(0.8f).toInt()
                        1f at durationMillis
                    },
                    repeatMode = RepeatMode.Reverse
                )
            )
            val animatedRotation by infiniteTransition.animateFloat(
                initialValue = 360f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = animDuration
                        360f at durationMillis.times(0) with FastOutSlowInEasing
                        0f at durationMillis.times(0.5f).toInt() with FastOutSlowInEasing
                        360f.times(2) at durationMillis
                    }
                )
            )
            val baseOrange = remember { Color(0xFFCD453F) }
            val bgDarkOrange = remember { Color(0xFF170C0A) }
            val screenHeightDp = LocalConfiguration.current.screenHeightDp.dp
            Box(
                modifier = Modifier.graphicsLayer {
                    rotationZ = animatedRotation
                    scaleX = animatedScale
                    scaleY = animatedScale
                },
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier, onDraw = {
                    drawCircle(
                        color = bgDarkOrange,
                        center = center,
                        radius = screenHeightDp.toPx().times(animatedBladeScale)
                    )
                    drawIntoCanvas {
                        it.drawCircle(
                            center = center,
                            radius = 100.dp.toPx(),
                            paint = Paint().apply {
                                asFrameworkPaint().setShadowLayer(
                                    60f,
                                    0f,
                                    0f,
                                    Color.White
                                        .copy(alpha = 0.3f)
                                        .toArgb()
                                )
                            }
                        )
                    }
                    drawCircle(
                        color = baseOrange,
                        radius = 90.dp.toPx()
                    )
                    drawCircle(
                        color = Color.Black,
                        radius = 64.dp.toPx(),
                        style = Stroke(
                            width = 3.dp.toPx()
                        )
                    )
                    drawCircle(
                        color = Color.Black,
                        radius = 16.dp.toPx()
                    )
                })
                SmallBlade(
                    modifier = Modifier
                        .graphicsLayer {
                            with(density) {
                                translationY = -70.dp.toPx()
                                alpha = 1 - animatedBladeScale
                            }
                        }
                )
                SmallBlade(
                    modifier = Modifier.graphicsLayer {
                        with(density) {
                            translationX = 64.dp.toPx()
                            translationY = 32.dp.toPx()
                            rotationZ = 120f
                            alpha = 1 - animatedBladeScale
                        }
                    }
                )
                SmallBlade(
                    modifier = Modifier.graphicsLayer {
                        with(density) {
                            translationX = -64.dp.toPx()
                            translationY = 32.dp.toPx()
                            rotationZ = 240f
                            alpha = 1 - animatedBladeScale
                        }
                    }
                )
                Image(
                    modifier = Modifier.size(154.dp).graphicsLayer {
                        translationX = -4.dp.toPx()
                        translationY = 12.dp.toPx()
                        rotationZ = 60f
                        scaleX = animatedBladeScale
                        scaleY = animatedBladeScale
                        alpha = animatedBladeScale
                    },
                    painter = androidx.compose.ui.res.painterResource(id = R.drawable.blade),
                    contentDescription = null
                )
                Canvas(
                    modifier = Modifier,
                    onDraw = {
                        drawCircle(
                            color = baseOrange,
                            radius = 17.dp.toPx().times(animatedBladeScale)
                        )
                    }
                )
            }
        }
        ShubhamSingh(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun SmallBlade(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .size(24.dp)
            .aspectRatio(2 / 3f)
            .then(modifier),
        shape = SmallBladeShape,
        color = Color.Black
    ) {}
}

val SmallBladeShape: Shape = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val baseWidth = 20f
        val baseHeight = 29f

        val path = Path()

        path.moveTo(10.5f, 9f)
        path.cubicTo(11.5f, 5.5f, 13f, 3f, 17.5f, 0f)
        path.cubicTo(10.8174f, 2.1578f, 4.3327f, 7.1003f, 1.1606f, 14.3197f)
        path.cubicTo(0.4197f, 15.7161f, 0f, 17.309f, 0f, 19f)
        path.cubicTo(0f, 24.5228f, 4.4772f, 29f, 10f, 29f)
        path.cubicTo(15.5228f, 29f, 20f, 24.5228f, 20f, 19f)
        path.cubicTo(20f, 13.6393f, 15.7819f, 9.2638f, 10.4837f, 9.0115f)
        path.lineTo(10.5f, 9f)
        path.close()

        return Outline.Generic(
            path
                .asAndroidPath()
                .apply {
                    transform(
                        Matrix().apply {
                            setScale(size.width / baseWidth, size.height / baseHeight)
                        }
                    )
                }
                .asComposePath()
        )
    }
}
