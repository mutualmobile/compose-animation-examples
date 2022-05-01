package dev.baseio.composeplayground.ui.animations.niket.dino_game.model

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.State

data class DinoModel(
    var xPosition : Float,
    var yPosition : Float = 1400f,
    var isRunning : Boolean = true,
    var isJumping : Boolean = false,
    var isCollided : Boolean = false,
    var animateX : Animatable<Float,AnimationVector1D>,
    var animateY : Animatable<Float,AnimationVector1D>,
    var dinoState : State<Float>
)