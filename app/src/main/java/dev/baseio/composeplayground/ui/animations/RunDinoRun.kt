package dev.baseio.composeplayground.ui.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R
import dev.baseio.composeplayground.contributors.Subir

@Composable
fun RunDinoRun(modifier: Modifier) {
    val launchState = remember { mutableStateOf(false) }
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Dino(
            isDinoRunning = launchState.value,
            maxWidth = maxWidth,
            maxHeight = maxHeight
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            Subir(Modifier.align(Alignment.Center))
        }
        LaunchButton(
            animationState = launchState.value,
            onToggleAnimationState = {
                launchState.value = launchState.value.not()
            })
    }
}

@Composable
fun Dino(
    isDinoRunning: Boolean,
    maxWidth: Dp,
    maxHeight: Dp
) {
    val dinoSize = 120.dp
    val resources: Painter
    val modifier: Modifier
    if (!isDinoRunning) {
        resources = painterResource(id = R.drawable.dino_idle)
        modifier = Modifier.offset(
            y = (maxHeight - dinoSize)
        )
    } else {
        val infiniteTransition = rememberInfiniteTransition()
        val engineState = infiniteTransition.animateFloat(
            initialValue = 0.0f,
            targetValue = 0.7f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1200,
                    easing = LinearEasing
                )
            )
        )
        val positionState = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(
                    durationMillis = 3600,
                    easing = LinearEasing
                )
            )
        )

        resources = when {
            engineState.value <= 0.1f -> painterResource(id = R.drawable.run_1)
            engineState.value <= 0.2f -> painterResource(id = R.drawable.run_2)
            engineState.value <= 0.3f -> painterResource(id = R.drawable.run_3)
            engineState.value <= 0.4f -> painterResource(id = R.drawable.run_4)
            engineState.value <= 0.5f -> painterResource(id = R.drawable.run_5)
            engineState.value <= 0.6f -> painterResource(id = R.drawable.run_6)
            engineState.value <= 0.7f -> painterResource(id = R.drawable.run_7)
            else -> painterResource(id = R.drawable.run_5)
        }

        modifier = Modifier.offset(
            x = (maxWidth - dinoSize) * positionState.value,
            y = (maxHeight - dinoSize) - ((maxHeight - dinoSize) * positionState.value)
        )
    }

    Image(
        modifier = modifier.size(dinoSize, dinoSize),
        painter = resources,
        contentDescription = "dino"
    )
}

@Composable
fun LaunchButton(
    animationState: Boolean,
    onToggleAnimationState: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        if (animationState) {
            Button(
                onClick = onToggleAnimationState,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "INITIAL POSITION")

            }
        } else {
            Button(onClick = onToggleAnimationState) {
                Text(text = "RUN")
            }
        }
    }

}

@Preview
@Composable
fun RunDinoRunPreview() {
    RunDinoRun(modifier = Modifier)
}