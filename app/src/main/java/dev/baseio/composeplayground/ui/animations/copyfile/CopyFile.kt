package dev.baseio.composeplayground.ui.animations.copyfile

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode.Restart
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
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
@Preview(group = "Copy File")
@Composable
fun CopyFile() {

  val infiniteTransition = rememberInfiniteTransition()
  val progressA by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = keyframes {
        delayMillis = 100
        durationMillis = 1800
        0.0f at 0 with LinearOutSlowInEasing
        0.3f at 600 with FastOutLinearInEasing
        1f at 1800 with LinearOutSlowInEasing
      },
      repeatMode = Restart
    )
  )
  val progressB by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = keyframes {
        delayMillis = 500
        durationMillis = 2000
        0.0f at 0 with LinearOutSlowInEasing
        0.4f at 800 with FastOutLinearInEasing
        1f at 2000 with LinearOutSlowInEasing
      },
      repeatMode = Restart
    )
  )

  val progressC by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = keyframes {
        delayMillis = 1000
        durationMillis = 2400
        0.0f at 0 with LinearOutSlowInEasing
        0.5f at 1000 with FastOutLinearInEasing
        1f at 2400 with LinearOutSlowInEasing
      },
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
                      width: 30,
                      height: 30,
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
                      width: 25,
                      height: 25,
                      rotationZ: 165,
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
                         frames: [5, 50, 95],
                         percentY: [0.8, 0.5, 0.8],
                         type: 'parentRelative'
                       }
                     ],
                      KeyCycles: [
                        {
                          target: ['a'],
                          frames: [0, 50, 100],
                          period: [0 , 0 , 5],
                          rotationX: [0, 45, 45],
                          rotationY: [0, 45, 45],
                        }
                      ]
                    }
                  }
                }
            }"""
        ),
        debug = EnumSet.of(MotionLayoutDebugFlags.NONE),
        progress = progressA
      ) {
        Image(
          painter = painterResource(id = drawable.ic_send),
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

      MotionLayout(
        modifier = Modifier
          .fillMaxWidth()
          .height(400.dp),
        motionScene = MotionScene(
          """{
                ConstraintSets: {
                  start: {
                    b: {
                      width: 30,
                      height: 30,
                      start: ['parent', 'start', 36],
                      bottom: ['parent', 'bottom', 24]
                    }
                  },
                  end: {
                    b: {
                      width: 25,
                      height: 25,
                      rotationZ: 165,
                      end: ['parent', 'end',36],
                      bottom: ['parent', 'bottom', 24]
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
                         target: ['b'],
                         frames: [5, 50, 95],
                         percentY: [0.8, 0.5, 0.8],
                         type: 'parentRelative'
                       }
                     ],
                     KeyCycles: [
                        {
                          target: ['b'],
                          frames: [0, 50, 100],
                          period: [0 , 0 , 5],
                          rotationX: [0, 45, 45],
                          rotationY: [0, 45, 45],
                        }
                      ]
                    }
                  }
                }
            }"""
        ),
        debug = EnumSet.of(MotionLayoutDebugFlags.NONE),
        progress = progressB
      ) {
        Image(
          painter = painterResource(id = drawable.ic_send),
          contentDescription = null,
          modifier = Modifier.layoutId("b")
        )
      }

      MotionLayout(
        modifier = Modifier
          .fillMaxWidth()
          .height(400.dp),
        motionScene = MotionScene(
          """{
                ConstraintSets: {
                  start: {
                    c: {
                      width: 30,
                      height: 30,
                      start: ['parent', 'start', 36],
                      bottom: ['parent', 'bottom', 24]
                    }
                  },
                  end: {
                    c: {
                      width: 25,
                      height: 25,
                      rotationZ: 165,
                      end: ['parent', 'end',36],
                      bottom: ['parent', 'bottom', 24]
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
                         target: ['c'],
                         frames: [5, 50, 95],
                         percentY: [0.8, 0.5, 0.8],
                         type: 'parentRelative'
                       }
                     ],
                     KeyCycles: [
                        {
                          target: ['c'],
                          frames: [0, 50, 100],
                          period: [0 , 0 , 5],
                          rotationX: [0, 45, 45],
                          rotationY: [0, 45, 45],
                        }
                      ]
                    }
                  }
                }
            }"""
        ),
        debug = EnumSet.of(MotionLayoutDebugFlags.NONE),
        progress = progressC
      ) {
        Image(
          painter = painterResource(id = drawable.ic_send),
          contentDescription = null,
          modifier = Modifier.layoutId("c")
        )
      }
    }
    PushpalRoy()
  }
}