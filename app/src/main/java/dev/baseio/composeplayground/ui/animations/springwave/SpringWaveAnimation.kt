package dev.baseio.composeplayground.ui.animations.springwave

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.PrateekSharma
import dev.baseio.composeplayground.ui.animations.springwave.AnimationState.End
import dev.baseio.composeplayground.ui.animations.springwave.AnimationState.Start
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SpringWaveAnimation() {
    val smallestWidth = 27f
    val smallestHeight = 9f

    var currentAnimationState by remember { mutableStateOf(Start)}

    val circle1offsetState = remember {
        Animatable(if(currentAnimationState==Start) 500f else 200f)
    }

    val circle2offsetState = remember {
        Animatable(if(currentAnimationState==Start) 497.5f else 197.5f)
    }

    val circle3offsetState = remember {
        Animatable(if(currentAnimationState==Start) 495f else 195f)
    }

    val circle4offsetState = remember {
        Animatable(if(currentAnimationState==Start) 492.5f else 192.5f)
    }

    val circle5offsetState = remember {
        Animatable(if(currentAnimationState==Start) 489.75f else 189.75f)
    }

    val circle6offsetState = remember {
        Animatable(if(currentAnimationState==Start) 487f else 187f)
    }

    val circle7offsetState = remember {
        Animatable(if(currentAnimationState==Start) 484f else 184f)
    }

    LaunchedEffect(key1 = currentAnimationState) {
        launch {
            /*
            * Need to launch these coroutines in different different
            * scopes because we want that they all run simultaneously
            * with there own delays from the global starting time.
            *
            * If we would have used these all animateTo() methods in
            * a single coroutine scope then they would run sequentially
            * one after another; means that the second ring would start moving
            * only after the damping effect of the first ring completes.
            * */

            launch {
                circle1offsetState.animateTo(
                    if(currentAnimationState==Start) 200f else 500f,
                    spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
            }
            launch {
                delay(40)
                circle2offsetState.animateTo(
                    if(currentAnimationState==Start) 197.5f else 497.5f,
                    spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
            }
            launch {
                delay(90)
                circle3offsetState.animateTo(
                    if(currentAnimationState==Start) 195f else 495f,
                    spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
            }
            launch {
                delay(130)
                circle4offsetState.animateTo(
                    if(currentAnimationState==Start) 192.5f else 492.5f,
                    spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
            }
            launch {
                delay(170)
                circle5offsetState.animateTo(
                    if(currentAnimationState==Start) 189.75f else 489.75f,
                    spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
            }
            launch {
                delay(210)
                circle6offsetState.animateTo(
                    if(currentAnimationState==Start) 187f else 487f,
                    spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
            }
            launch {
                delay(250)
                circle7offsetState.animateTo(
                    if(currentAnimationState==Start) 184f else 484f,
                    spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow)
                )
            }
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Oval3D(givenSurfaceWidth = smallestWidth, givenSurfaceHeight = smallestHeight, modifier = Modifier.absoluteOffset(this.maxWidth/2, circle1offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*2, givenSurfaceHeight = smallestHeight*2.1f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(13).dp, circle2offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*3, givenSurfaceHeight = smallestHeight*3.2f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(26.5).dp, circle3offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*4, givenSurfaceHeight = smallestHeight*4.4f, modifier = Modifier.absoluteOffset(this.maxWidth/2-40.dp, circle4offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*5, givenSurfaceHeight = smallestHeight*5.6f, modifier = Modifier.absoluteOffset(this.maxWidth/2-53.dp, circle5offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*6, givenSurfaceHeight = smallestHeight*6.9f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(67).dp, circle6offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*7, givenSurfaceHeight = smallestHeight*8.2f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(80).dp, circle7offsetState.value.dp))

        PrateekSharma(
            Modifier.background(Color.White).absoluteOffset(y=5.dp)
        )

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize(align = Alignment.Center),
            onClick = {
                if(currentAnimationState == End) {
                    currentAnimationState = Start
                } else {
                    currentAnimationState = End
                }
            }) {
            Text(
                text = if(currentAnimationState==Start) {
                    "Move the wave to down"
                } else {
                    "Move the wave to Up"
                }
            )
        }
    }
}

enum class AnimationState {
    Start, End
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SpringWaveAnimPrev() {
    SpringWaveAnimation()
}