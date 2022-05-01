package dev.baseio.composeplayground.ui.animations.niket.dino_game.util

import androidx.compose.ui.unit.Dp
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.CloudCoordinates
import dev.baseio.composeplayground.ui.animations.niket.dino_game.model.ObstacleModel
import dev.baseio.composeplayground.R
import kotlin.random.Random

/**
 * This create the Random Cactus Obstacles,
 * The Random Number btw 800-1000 help us to get essentials gap btw consecutive obstacles.
 * MaxScreen Width value is btw 600-700 that's why we multiply it with 2.5F value
 */
fun createRandomObstaclesList(
    maxScreenWidth: Dp
): MutableList<ObstacleModel> {
    val cactusObstaclesList = mutableListOf<ObstacleModel>()
    val cactusNumber = Random.nextInt(100, 150)
    for (i in 1..cactusNumber) {
        cactusObstaclesList.add(
            element = ObstacleModel(
                id = i * System.currentTimeMillis(),
                xPosition = maxScreenWidth.value * 2.5f + (i * Random.nextInt(600, 800)),
                yPosition = 1400f
            )
        )
    }
    return cactusObstaclesList
}

/**
 * This create the Random Clouds Obstacles,
 * The Random Number btw 500-1000 help us to get essentials gap btw consecutive clouds.
 *  MaxScreen Width value is btw 600-700 that's why we multiply it with 2.5F value
 */
fun createRandomCloudsList(
    maxScreenWidth: Dp
): MutableList<CloudCoordinates> {

    val cloudList = mutableListOf<CloudCoordinates>()
    val cloudNumber = Random.nextInt(from = 4, 10)
    for (i in 0..cloudNumber) {
        cloudList.add(
            element = CloudCoordinates(
                xPosition = maxScreenWidth.value * 2.5f + (i * Random.nextInt(500, 1000)),
                yPosition = Random.nextInt(150, 750).toFloat(),
            )
        )
    }

    return cloudList
}

fun randomCactiObstaclesListItem(): Int {
    val randomCactiObstaclesList = mutableListOf(
        R.drawable.cacti_large_1,
        R.drawable.cacti_large_2,
        R.drawable.cacti_small_1,
        R.drawable.cacti_small_2,
        R.drawable.cacti_small_3,
        R.drawable.cacti_group,
    )

    return randomCactiObstaclesList.random()
}
