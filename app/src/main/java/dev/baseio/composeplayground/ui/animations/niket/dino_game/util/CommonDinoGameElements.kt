package dev.baseio.composeplayground.ui.animations.niket.dino_game.util

import androidx.compose.runtime.State
import dev.baseio.composeplayground.R

/**
 * Animate Dino at specific conditions. 
 */
fun getDinoRunningResource(
    dinoState: State<Float>,
    isCollided: Boolean,
    isJumping : Boolean
): Int {

    if (isCollided) {
        return R.drawable.dino_collided_2
    }
    if (isJumping){
        return R.drawable.dino_1_still
    }
    when (dinoState.value) {
        in 0.0f..1.0f -> {
            return (R.drawable.dino_right_leg)
        }
        in 1.0f..2.0f -> {
            return (R.drawable.dino_left_leg)
        }
        in 2.0f..3.0f -> {
            return (R.drawable.dino_right_leg)
        }
        in 3.0f..4.0f -> {
            return (R.drawable.dino_left_leg)
        }
        in 4.0f..5.0f -> {
            return (R.drawable.dino_right_leg)
        }
    }

    return R.drawable.dino_1_still
}