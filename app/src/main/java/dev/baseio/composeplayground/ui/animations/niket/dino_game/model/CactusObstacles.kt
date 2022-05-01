package dev.baseio.composeplayground.ui.animations.niket.dino_game.model

import androidx.compose.runtime.State

data class CactusObstacles(
    var obstacleModel: ObstacleModel,
    var isCollision : Boolean = false,
    var isPassedOutOfScreen : Boolean = false,
    var cactusObstaclesState : State<Float>
)