package dev.baseio.composeplayground.ui.animations.niket.dino_game.game

import android.content.res.Resources
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.CactusObstacles
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.CloudModel
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.DinoGameModel
import dev.baseio.composeplayground.ui.animations.niket.dino_game.util.createRandomCloudsList
import dev.baseio.composeplayground.ui.animations.niket.dino_game.util.createRandomObstaclesList
import dev.baseio.composeplayground.contributors.NiketJain
import dev.baseio.composeplayground.ui.theme.DarkDinoGameBackground
import kotlinx.coroutines.launch

@Composable
fun DinoGameView(
    windowResource: Resources,
    maxWidth: Dp,
    maxHeight: Dp,
) {
    val dinoGameState = dinoGameState(
        createRandomObstaclesList(maxHeight),
        createRandomCloudsList(maxWidth)
    )

    /**
     * Drawing every object at the canvas
     */
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        CreateGameCanvas(
            gameState = dinoGameState,
            drawGameView = { drawScope ->
                drawScope.drawGround(resource = windowResource)
                drawScope.drawDino(
                    gameState = dinoGameState,
                    resource = windowResource
                )
                removeThePassedObstacles(dinoGameState)
                for (obstacles: CactusObstacles in dinoGameState.obstaclesList) {
                    drawScope.drawObstacles(
                        resource = windowResource,
                        obstacles = obstacles,
                        dinoGameState = dinoGameState
                    )
                }
                for (cloud: CloudModel in dinoGameState.cloudList) {
                    drawScope.drawCloud(
                        resources = windowResource,
                        cloudModel = cloud
                    )
                }
            }
        )
        NiketJain()
    }
}

@Composable
private fun CreateGameCanvas(
    gameState: DinoGameModel,
    drawGameView: (DrawScope) -> Unit
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = DarkDinoGameBackground
            )
            .pointerInput(Unit) {
                /** When Clicked make the Dino Jump **/
                detectTapGestures {
                    gameState.dino.isJumping = true
                    if (gameState.dino.isJumping) {
                        gameState.coroutineScope.launch {
                            gameState.dino.isRunning = false
                            gameState.dino.animateY.animateTo(
                                targetValue = gameState.dino.yPosition - 175,
                                animationSpec = tween(
                                    durationMillis = 275,
                                    easing = LinearOutSlowInEasing
                                )
                            )
                            gameState.dino.animateY.animateTo(
                                targetValue = gameState.dino.yPosition,
                                animationSpec = tween(
                                    durationMillis = 325,
                                    easing = LinearOutSlowInEasing
                                )
                            )
                            gameState.dino.isRunning = true
                            gameState.dino.isJumping = false
                        }
                    }
                }
            },
    ) {
        drawGameView(this)
    }
}

/*
    Removing the Obstacles to save the Resources
    We are removing the item that has been passed the X-axis screen.
 */
fun removeThePassedObstacles(gameState: DinoGameModel) {
    gameState.obstaclesList.removeAll { obstacles ->
        obstacles.isPassedOutOfScreen && (obstacles.obstacleModel.xPosition <= 0)
    }

}
