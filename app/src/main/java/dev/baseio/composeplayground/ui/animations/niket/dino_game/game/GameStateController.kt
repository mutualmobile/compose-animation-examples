package dev.baseio.composeplayground.ui.animations.niket.dino_game.game

import android.content.res.Resources
import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.imageResource
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.CactusObstacles
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.CloudModel
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.DinoGameModel
import dev.baseio.composeplayground.ui.animations.niket.dino_game.util.getDinoRunningResource
import dev.baseio.composeplayground.ui.animations.niket.dino_game.util.randomCactiObstaclesListItem
import dev.baseio.composeplayground.R

fun DrawScope.drawDino(
    resource: Resources,
    gameState: DinoGameModel
) {
    val paint = Paint().asFrameworkPaint()
    with(gameState) {

        /**
         * This is to debug the code, use to display the coordinates of the animating objects.
         */
//        paint.apply {
//            isAntiAlias = true
//            textSize = 24f
//            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
//        }
//        drawIntoCanvas {
//            it.nativeCanvas.drawText(
//                "${gameState.dino.xPosition}, ${gameState.dino.yPosition}",
//                gameState.dino.xPosition,
//                gameState.dino.yPosition - 10,
//                paint
//            )
//        }

        drawImage(
            image = ImageBitmap.imageResource(
                id = getDinoRunningResource(
                    dinoState = dino.dinoState,
                    isCollided = dino.isCollided,
                    isJumping = dino.isJumping
                ),
                res = resource
            ),
            Offset(
                x = dino.animateX.value,
                y = dino.animateY.value
            )
        )
    }
}

fun DrawScope.drawGround(
    resource: Resources
) {
    drawImage(
        image = ImageBitmap.imageResource(
            id = R.drawable.dino_game_ground,
            res = resource
        ),
        Offset(
            x = 0f,
            y = 1400f
        )
    )
}

fun DrawScope.drawCloud(
    resources: Resources,
    cloudModel: CloudModel,
) {
    with(cloudModel) {
        cloudModel.cloudCoordinates.xPosition = cloudState.value
        drawImage(
            image = ImageBitmap.imageResource(
                id = R.drawable.dino_game_cloud,
                res = resources,
            ),
            Offset(
                x = cloudCoordinates.xPosition,
                y = cloudCoordinates.yPosition,
            ),
        )
    }
}

fun DrawScope.drawObstacles(
    resource: Resources,
    obstacles: CactusObstacles,
    dinoGameState: DinoGameModel
) {
    val paint = Paint().asFrameworkPaint()
    with(obstacles) {
        isCollision = detectCollision(dinoGameState)
        checkCollisionAndBehindObstaclesState(dinoGameState, isCollision, obstacles)
        obstacleModel.xPosition = cactusObstaclesState.value
        val randomObstacles = randomCactiObstaclesListItem()

        /**
         * This is to debug the code, use to display the coordinates of the Moving objects.
         */
//        paint.apply {
//            isAntiAlias = true
//            textSize = 24f
//            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
//        }
//        drawIntoCanvas {
//            it.nativeCanvas.drawText(
//                "${obstacles.obstacleModel.xPosition}, ${obstacles.obstacleModel.yPosition}",
//                obstacles.obstacleModel.xPosition,
//                obstacles.obstacleModel.yPosition - 10,
//                paint
//            )
//        }

        drawImage(
            image = ImageBitmap.imageResource(
                res = resource,
                id = R.drawable.cacti_small_1
            ),
            Offset(
                x = obstacleModel.xPosition,
                y = 1350f
            )
        )
    }
}

/**
 * This is WIP
 */
fun CactusObstacles.detectCollision(dinoGameState: DinoGameModel): Boolean {
    return (dinoGameState.dino.xPosition <= obstacleModel.xPosition && dinoGameState.dino.xPosition == obstacleModel.xPosition + 5)
            && (dinoGameState.dino.yPosition <= this.obstacleModel.yPosition && dinoGameState.dino.yPosition - 5 >= this.obstacleModel.yPosition)
}

fun checkCollisionAndBehindObstaclesState(
    dinoGameState: DinoGameModel,
    collision: Boolean,
    queriedObstacles: CactusObstacles
) {
    if (collision) {
        dinoGameState.obstaclesList.find { obstacle ->
            obstacle.obstacleModel.id == queriedObstacles.obstacleModel.id
        }?.apply {
            dinoGameState.dino.isRunning = false
            dinoGameState.dino.isCollided = true
            Log.d(
                "Collision",
                "checkCollisionAndBehindObstaclesState: ${dinoGameState.dino.isCollided}"
            )
        }
    }

    if (queriedObstacles.obstacleModel.xPosition <= 0) {
        dinoGameState.obstaclesList.find { obstacle ->
            obstacle.obstacleModel.id == queriedObstacles.obstacleModel.id
        }?.apply {
            isPassedOutOfScreen = true
        }
    }
}
