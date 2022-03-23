package dev.baseio.composeplayground

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import dev.baseio.composeplayground.ui.animations.AndroidMadSkills
import dev.baseio.composeplayground.ui.animations.BellAnimation
import dev.baseio.composeplayground.ui.animations.ChatMessageReactions
import dev.baseio.composeplayground.ui.animations.Github404
import dev.baseio.composeplayground.ui.animations.GlowingRingLoader
import dev.baseio.composeplayground.ui.animations.IOSSleepSchedule
import dev.baseio.composeplayground.ui.animations.LikeAnimation
import dev.baseio.composeplayground.ui.animations.MenuToClose
import dev.baseio.composeplayground.ui.animations.NetflixIntroAnimation
import dev.baseio.composeplayground.ui.animations.ScalingRotatingLoader
import dev.baseio.composeplayground.ui.animations.ShootingStarsAnimation
import dev.baseio.composeplayground.ui.animations.TwitterSplashAnimation
import dev.baseio.composeplayground.ui.animations.YahooWeatherAndSun
import dev.baseio.composeplayground.ui.animations.copyfile.CopyFile
import dev.baseio.composeplayground.ui.animations.copyfile.CopyFile2
import dev.baseio.composeplayground.ui.animations.planetarysystem.PlanetarySystem
import dev.baseio.composeplayground.ui.animations.pulltorefresh.PullToRefreshOne
import dev.baseio.composeplayground.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalPagerApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    window.apply {
      clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
      addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        decorView.systemUiVisibility =
          View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
      }
      statusBarColor = Color.TRANSPARENT
    }
    super.onCreate(savedInstanceState)

    setContent {
      ComposePlaygroundTheme {
        // A surface container using the 'background' color from the theme
        MaterialTheme() {
          ProvideWindowInsets() {
            Surface(color = MaterialTheme.colors.background) {
              AnimationsPager()
            }
          }
        }
      }
    }
  }

  @OptIn(ExperimentalPagerApi::class)
  @Composable
  private fun AnimationsPager() {
    val pagerState = rememberPagerState()
    Box(
      modifier = Modifier
        .fillMaxSize()
    ) {
      HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        count = 17, state = pagerState,
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
          0 -> {
            Box(Modifier.fillMaxSize()) {
              YahooWeatherAndSun(Modifier.align(Alignment.Center))
            }
          }
          10 -> {
            Box(modifier = Modifier.fillMaxSize()) {
              IOSSleepSchedule()
            }
          }
          12 -> {
            TwitterSplashAnimation()
          }
          13 -> {
            AndroidMadSkills()
          }
          14 -> {
            ShootingStarsAnimation()
          }
          15 -> {
            Box(modifier = Modifier.fillMaxSize()) {
              CopyFile()
            }
          }
          16 -> {
            Box(modifier = Modifier.fillMaxSize()) {
              CopyFile2()
            }
          }
          6 -> {
            NetflixIntroAnimation()
          }
          11 -> {
            Box(modifier = Modifier.fillMaxSize()) {
              Github404(Modifier)
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
          .align(Alignment.BottomCenter)
          .padding(16.dp)
      )
    }
  }
}