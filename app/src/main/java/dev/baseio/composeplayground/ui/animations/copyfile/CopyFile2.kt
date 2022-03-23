package dev.baseio.composeplayground.ui.animations.copyfile

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode.Restart
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionLayoutDebugFlags
import androidx.constraintlayout.compose.MotionScene
import dev.baseio.composeplayground.R.drawable
import dev.baseio.composeplayground.contributors.PushpalRoy
import dev.baseio.composeplayground.ui.theme.Typography
import java.util.EnumSet

@OptIn(ExperimentalMotionApi::class)
@Preview(group = "Copy File 2")
@Composable
fun CopyFile2() {

  val infiniteTransition = rememberInfiniteTransition()
  val progressA by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(delayMillis = 200, durationMillis = 2800, easing = FastOutLinearInEasing),
      repeatMode = Restart
    )
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(Color(0xFF322F36)),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      modifier = Modifier.wrapContentHeight(),
      text = "Copying files, wait...",
      style = Typography.subtitle1.copy(color = Color.White),
    )
    Surface(
      modifier = Modifier
        .wrapContentSize()
        .padding(bottom = 150.dp)
    ) {
      MotionLayout(
        modifier = Modifier
          .fillMaxWidth()
          .height(400.dp)
          .background(Color(0xFF322F36)),
        motionScene = MotionScene(
          """{
                ConstraintSets: {
                  start: {
                    a: {
                      width: 40,
                      height: 40,
                      start: ['parent', 'start', 36],
                      bottom: ['parent', 'bottom', 24]
                    },
                    folder_shared: {
                      width: 50,
                      height: 50,
                      start: ['parent', 'start', 32],
                      bottom: ['parent', 'bottom', 16]
                    },
                    folder: {
                      width: 50,
                      height: 50,
                      end: ['parent', 'end',32],
                      bottom: ['parent', 'bottom', 16]
                    }
                  },
                  end: {
                    a: {
                      width: 40,
                      height: 40,
                      rotationY: 185,
                      end: ['parent', 'end', 36],
                      bottom: ['parent', 'bottom', 24]
                    },
                    folder_shared: {
                      width: 50,
                      height: 50,
                      start: ['parent', 'start', 32],
                      bottom: ['parent', 'bottom', 16]
                    },
                    folder: {
                      width: 50,
                      height: 50,
                      end: ['parent', 'end',32],
                      bottom: ['parent', 'bottom', 16]
                    }
                  }
                },
                Transitions: {
                  default: {
                    from: 'start',
                    to: 'end',
                    pathMotionArc: 'a',
                    KeyFrames: {
                     KeyPositions: [
                       {
                         target: ['a'],
                         frames: [1, 10, 50, 99, 99],
                         percentY: [0.9, 0.6, 0.6, 0.6, 0.9],
                         type: 'parentRelative'
                       }
                     ]
                    }
                  }
                }
            }"""
        ),
        debug = EnumSet.of(MotionLayoutDebugFlags.SHOW_ALL),
        progress = progressA
      ) {
        Image(
          painter = painterResource(id = drawable.ic_file),
          contentDescription = null,
          modifier = Modifier.layoutId("a")
        )
        Image(
          painter = painterResource(id = drawable.ic_folder_shared),
          contentDescription = null,
          modifier = Modifier.layoutId("folder_shared")
        )
        Image(
          painter = painterResource(id = drawable.ic_folder),
          contentDescription = null,
          modifier = Modifier.layoutId("folder")
        )
      }
    }
    PushpalRoy()
  }
}