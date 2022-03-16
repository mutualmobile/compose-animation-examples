package dev.baseio.composeplayground.ui.animations.springwave

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp


/*
* Give [ Width = 3 * Height ] to see better view of the oval in 3D
* */
@Composable
fun Oval3D(
    givenSurfaceWidth: Float,
    givenSurfaceHeight: Float,
    modifier: Modifier
) {
    Surface(modifier = modifier
        .width(givenSurfaceWidth.dp)
        .height(givenSurfaceHeight.dp),
        color = Color.White,
        shape = object : Shape {
            override fun createOutline(
                size: Size,
                layoutDirection: LayoutDirection,
                density: Density
            ): Outline {
                val thickness = 14f
                val p1 = Path().apply {
                    addOval(Rect(0f, 0f, size.width, size.height))
                }
                val p2 = Path().apply {
                    addOval(
                        Rect(
                            thickness + 5,
                            thickness - 9,
                            size.width - thickness - 5,
                            size.height - thickness - 3
                        )
                    )
                }
                val p3 = Path()
                p3.op(p1, p2, PathOperation.Difference)
                return Outline.Generic(p3)
            }
        }
    ) {
    }
}

@Composable
@Preview
fun Oval3DPrev() {
    Oval3D(givenSurfaceWidth = 99f, givenSurfaceHeight = 33f, Modifier)
}