package dev.baseio.composeplayground.ui.animations.adityabhawsar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.AdityaBhawsar

@Composable
fun FiSplashAnimation() {

  Box(
    Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {



    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .align(Alignment.TopCenter)
    ) {
      AdityaBhawsar(
        Modifier
          .align(Alignment.Center)
          .background(Color.LightGray)
      )
    }
  }
}