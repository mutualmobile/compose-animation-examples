package dev.baseio.composeplayground.ui.animations

import dev.baseio.composeplayground.contributors.AdityaBhawsar
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.R

@Composable
fun BreatheAnimation(modifier: Modifier = Modifier) {

  Box(
    modifier
      .fillMaxSize()
      .background(Color.Black)
  ) {

    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {



    }

    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .align(Alignment.BottomEnd)
    ) {
      AdityaBhawsar(
        Modifier
          .align(Alignment.Center)
          .background(Color.LightGray))
    }
  }
}