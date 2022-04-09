package dev.baseio.composeplayground.ui.animations.adityabhawsar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.baseio.composeplayground.contributors.AdityaBhawsar

@Composable
fun AlarmSliderAnimation() {

  Box(modifier = Modifier
    .fillMaxSize()
    .background(Color.Black)){

    Box(modifier = Modifier
      .fillMaxWidth()
      .align(Alignment.Center)){

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