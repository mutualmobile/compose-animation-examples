package dev.baseio.composeplayground.ui.animations.niket.dino_game.model

import kotlinx.coroutines.CoroutineScope

data class DinoGameModel(
    val obstaclesList : MutableList<CactusObstacles>,
    val dino : DinoModel,
    val cloudList : MutableList<CloudModel>,
    val coroutineScope: CoroutineScope,
    val currentScore : Int = 0,
    val highestScore : Int = 0
)