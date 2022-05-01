package dev.baseio.composeplayground.ui.animations.niket.dino_game.game

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.CactusObstacles
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.CloudCoordinates
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.CloudModel
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.DinoGameModel
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.DinoModel
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.ObstacleModel

/*
    Various methods to handle and change the state of the
    different objects.
 */
@Composable
fun dinoGameState(
    obstaclesList: MutableList<ObstacleModel>,
    cloudList: MutableList<CloudCoordinates>,
): DinoGameModel {
    val animationScope = rememberCoroutineScope()

    val obstaclesList = createRandomGameObstacles(obstaclesList = obstaclesList)
    val cloudList = createRandomClouds(cloudList = cloudList)

    // Create Dino
    val dinoXPosition by remember { mutableStateOf(200f) }
    val dinoYPosition by remember { mutableStateOf(1320f) }
    val animatableDinoX = remember { Animatable(initialValue = dinoXPosition) }
    val animatableDinoY = remember { Animatable(initialValue = dinoYPosition) }
    val isRunning by remember { mutableStateOf(true) }
    val isJumping by remember { mutableStateOf(false) }
    val isCollided by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition()
    val runningDinoAnimationState = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 400,
                easing = LinearEasing
            )
        )
    )

    val dino = DinoModel(
        xPosition = dinoXPosition,
        yPosition = dinoYPosition,
        isRunning = isRunning,
        isJumping = isJumping,
        isCollided = isCollided,
        animateX = animatableDinoX,
        animateY = animatableDinoY,
        dinoState = runningDinoAnimationState
    )

    return DinoGameModel(
        dino = dino,
        coroutineScope = animationScope,
        obstaclesList = obstaclesList,
        cloudList = cloudList
    )
}

@Composable
fun createRandomClouds(cloudList: MutableList<CloudCoordinates>): MutableList<CloudModel> {

    val isMoving by remember { mutableStateOf(true) }
    val cloudStateList = mutableListOf<CloudModel>()

    for (cloud: CloudCoordinates in cloudList) {
        val infiniteTransition = rememberInfiniteTransition()
        val floatingCloudToLeftInfiniteTransition = infiniteTransition.animateFloat(
            initialValue = cloud.xPosition,
            targetValue =  -cloud.xPosition,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = (cloud.xPosition * 15).toInt() / 2,
                    easing = LinearEasing
                )
            )
        )

        cloudStateList.add(
            CloudModel(
                cloudCoordinates = cloud,
                isMoving = isMoving,
                cloudState = floatingCloudToLeftInfiniteTransition
            )
        )
    }

    return cloudStateList
}


@Composable
fun createRandomGameObstacles(
    obstaclesList: MutableList<ObstacleModel>
): MutableList<CactusObstacles> {
    val cactusObstaclesList = mutableListOf<CactusObstacles>()

    for (obstacles: ObstacleModel in obstaclesList) {
        val isCollision by remember {
            mutableStateOf(false)
        }

        val infiniteObstaclesTransition = rememberInfiniteTransition()
        val obstaclesTransition = infiniteObstaclesTransition.animateFloat(
            initialValue = obstacles.xPosition,
            targetValue = -obstacles.xPosition.div(1000f),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = (obstacles.xPosition * 5).toInt() / 2,
                    easing = LinearEasing
                )
            )
        )

        cactusObstaclesList.add(
            CactusObstacles(
                obstacleModel = obstacles,
                isCollision = isCollision,
                isPassedOutOfScreen = false,
                cactusObstaclesState = obstaclesTransition
            )
        )
    }

    return cactusObstaclesList
}