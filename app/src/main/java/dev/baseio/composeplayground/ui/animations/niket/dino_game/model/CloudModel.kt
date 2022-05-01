package dev.baseio.composeplayground.ui.animations.niket.dino_game.model

import androidx.compose.runtime.State
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.CloudCoordinates

data class CloudModel(
    var cloudCoordinates: CloudCoordinates,
    var isMoving : Boolean = true,
    var cloudState : State<Float>
)