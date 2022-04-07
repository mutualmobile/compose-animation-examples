package dev.baseio.composeplayground

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import dev.baseio.composeplayground.ui.animations.*
import dev.baseio.composeplayground.ui.animations.planetarysystem.PlanetarySystem
import dev.baseio.composeplayground.ui.animations.pulltorefresh.PullToRefreshOne
import dev.baseio.composeplayground.contributors.ShubhamSingh
import dev.baseio.composeplayground.ui.animations.ChatMessageReactions
import dev.baseio.composeplayground.ui.animations.LikeAnimation
import dev.baseio.composeplayground.ui.animations.LogoAnimation
import dev.baseio.composeplayground.ui.animations.MenuToClose
import dev.baseio.composeplayground.ui.animations.PinterestLogoProgressAnim
import dev.baseio.composeplayground.ui.animations.SyncingLoader
import dev.baseio.composeplayground.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalPagerApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    window.apply {
      clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
      addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
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
              val pagerState = rememberPagerState(initialPage = 15)
              Column(
                modifier = Modifier
                  .fillMaxSize()

              ) {
                HorizontalPager(
                  modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                  count = 15, state = pagerState,
                ) { page ->
                  // Our page content
                  when (page) {
                    0 -> {
                      Box(modifier = Modifier.fillMaxSize()) {
                        IOSSleepSchedule()
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
                    4 -> {
                      PullToRefreshOne()
                    }
                    5 -> {
                      Box(Modifier.fillMaxSize()) {
                        BellAnimation(Modifier.align(Alignment.Center))
                      }
                    }
                    6 -> {
                      Box(Modifier.fillMaxSize()) {
                        YahooWeatherAndSun(Modifier.align(Alignment.Center))
                      }
                    }
                    7 -> {
                      Box(Modifier.fillMaxSize()) {
                        GlowingRingLoader(Modifier.align(Alignment.Center))
                      }
                    }
                    8 -> {
                      Box(Modifier.fillMaxSize()) {
                        PlanetarySystem(Modifier.align(Alignment.Center))
                      }
                    }
                    9 -> {
                      Box(modifier = Modifier.fillMaxSize()) {
                        ScalingRotatingLoader()
                      }
                    }
                    10 -> {
                      Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                        PinterestLogoProgressAnim()
                        ShubhamSingh()
                      }
                    }
                    11 -> {
                      Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                        LogoAnimation()
                        ShubhamSingh()
                      }
                    }
                    12 -> {
                      Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {
                        SyncingLoader()
                        ShubhamSingh()
                      }
                    }
                    13 -> {
                      Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                        JumpingDotsLoadingAnimation()
                        ShubhamSingh()
                      }
                    }
                    14 -> {
                      Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        BottleLoadingAnimation()
                        ShubhamSingh(modifier = Modifier.align(Alignment.BottomCenter))
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
}