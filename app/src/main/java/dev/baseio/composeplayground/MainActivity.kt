package dev.baseio.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import dev.baseio.composeplayground.ui.animations.*
import dev.baseio.composeplayground.ui.animations.planetarysystem.PlanetarySystem
import dev.baseio.composeplayground.ui.animations.pulltorefresh.PullToRefreshOne
import dev.baseio.composeplayground.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalPagerApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposePlaygroundTheme {
        // A surface container using the 'background' color from the theme
        MaterialTheme() {
          Surface(color = MaterialTheme.colors.background) {
            val pagerState = rememberPagerState()
            Column(modifier = Modifier.fillMaxSize()) {
              HorizontalPager(
                modifier = Modifier
                  .weight(1f)
                  .fillMaxWidth(),
                count = 10, state = pagerState,
              ) { page ->
                // Our page content
                when (page) {
                  4 -> {
                    PullToRefreshOne()
                  }
                  7 -> {
                    Box(Modifier.fillMaxSize()) {
                      GlowingRingLoader(Modifier.align(Alignment.Center))
                    }
                  }
                  6 -> {
                    Box(Modifier.fillMaxSize()) {
                      YahooWeatherAndSun(Modifier.align(Alignment.Center))
                    }
                  }
                  0 -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                      IOSSleepSchedule()
                    }
                  }
                  9 -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                      ScalingRotatingLoader()
                    }
                  }
                  8 -> {
                    Box(Modifier.fillMaxSize()) {
                      PlanetarySystem(Modifier.align(Alignment.Center))
                    }
                  }
                  1 -> {
                    Box(Modifier.fillMaxSize()) {
                      LikeAnimation(Modifier.align(Alignment.Center))
                    }
                  }
                  2 -> {
                    Box(Modifier.fillMaxSize()) {
                      ChatMessageReactions(Modifier.align(Alignment.Center))
                    }
                  }
                  3 -> {
                    Box(Modifier.fillMaxSize()) {
                      MenuToClose(Modifier.align(Alignment.Center))
                    }
                  }
                  5 -> {
                    Box(Modifier.fillMaxSize()) {
                      BellAnimation(Modifier.align(Alignment.Center))
                    }
                  }
                }
              }
              HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                  .align(Alignment.CenterHorizontally)
                  .padding(16.dp),
              )
            }
          }
        }
      }
    }
  }
}