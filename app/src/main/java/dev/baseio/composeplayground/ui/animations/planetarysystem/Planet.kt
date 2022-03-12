package dev.baseio.composeplayground.ui.animations.planetarysystem

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

data class Planet(
  var x: Float,
  var y: Float,
  val radius: Float,
  val planetColor: Color,
  val velocity: Float,
  val orbitRadius: Float,
) {
  var radian: Float = 0f
  var moon = Moon()
  var startX = x
  var startY = y

  init {
    moon.prepare(this)
  }

  private fun draw(canvas: Canvas, centerOffset: Offset) {
    // arc
    canvas.drawCircle(centerOffset, this.orbitRadius, Paint().apply {
      this.color = Color.Gray
      this.style = PaintingStyle.Stroke
      this.strokeWidth = 4f
    })

    //planet
    canvas.drawCircle(Offset(x, y), this.radius, Paint().apply {
      this.color = planetColor
      this.style = PaintingStyle.Fill
    })

    //moon
    canvas.drawCircle(Offset(moon.x, moon.y), 2f, Paint().apply {
      this.color = Color.Gray
      this.style = PaintingStyle.Fill
    })
  }

  fun update(canvas: Canvas, centerOffset: Offset) {
    this.draw(canvas, centerOffset)
    if (this.velocity > 0) {
      this.radian += this.velocity
      this.moon.radian += this.moon.velocity
      this.moon.x =
        (this.x + cos(this.moon.radian.toDouble()) * (this.radius + 5)).toFloat()
      this.moon.y =
        (this.y + sin(this.moon.radian.toDouble()) * (this.radius + 5)).toFloat()

      this.x = (this.startX + cos(this.radian.toDouble()) * this.orbitRadius).toFloat()
      this.y = (this.startY + sin(this.radian.toDouble()) * this.orbitRadius).toFloat()
    }
  }
}

class SolarSystem(private val centerOffset: Offset) {
  private val planets by lazy {
    val planets = mutableListOf<Planet>()
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 35f,
        velocity = 0f,
        orbitRadius = 0f,
        color = Color(0xfff9d71c)
      )
    ); // sun
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 5f,
        velocity = 50f,
        orbitRadius = 65f,
        color = Color.Gray
      )
    ); // mercury
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 10f,
        velocity = 40f,
        orbitRadius = 90f,
        color = Color(0xffFFA500)
      )
    ); // venus
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 15f,
        velocity = 30f,
        orbitRadius = 125f,
        color = Color.Blue
      )
    ); // earth
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 20f,
        velocity = 35f,
        orbitRadius = 175f,
        color = Color.Red
      )
    ); // mars
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 25f,
        velocity = 30f,
        orbitRadius = 225f,
        color = Color(0xffFFA500)
      )
    ); // jupiter
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 20f,
        velocity = 25f,
        orbitRadius = 275f,
        color = Color(0xfff9d71c)
      )
    ); // saturn
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 15f,
        velocity = 20f,
        orbitRadius = 325f,
        color = Color.Blue
      )
    ); // uranus
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 25f,
        velocity = 15f,
        orbitRadius = 375f,
        color = Color(0xff800080)
      )
    ); // neptune
    planets.add(
      getPlanetForOptions(
        centerOffset.x,
        centerOffset.y,
        radius = 7f,
        velocity = 10f,
        orbitRadius = 450f,
        color = Color.Gray
      )
    ) // pluto
    planets
  }


  private fun getPlanetForOptions(
    centerX: Float,
    centerY: Float,
    radius: Float,
    color: Color,
    velocity: Float,
    orbitRadius: Float
  ): Planet {
    return Planet(
      centerX,
      centerY,
      radius,
      color,
      velocity / 1000,
      orbitRadius
    )
  }

  fun animate(canvas: Canvas) {
    planets.forEach {
      it.update(canvas, centerOffset)
    }
  }
}

class Moon {
  var x: Float = 0f
  var y: Float = 0f
  var radian: Float = 0f
  var velocity: Float = 0f

  fun prepare(planet: Planet) {
    this.x = planet.x + planet.orbitRadius + planet.radius
    this.y = planet.y
    this.radian = 0f
    this.velocity = (Random.nextFloat() + 0.1).div(30).toFloat()
  }

}

