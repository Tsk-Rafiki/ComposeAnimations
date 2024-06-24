package com.example.composeanimations.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
internal fun CanvasAnimationScreen(modifier: Modifier = Modifier) {
    val firstPointOffset = remember { mutableStateOf(Offset(200f, 300f)) }
    val secondPointOffset = remember { mutableStateOf(Offset(400f, 300f)) }
    val thirdPointOffset = remember { mutableStateOf(Offset(600f, 300f)) }
    val forthPointOffset = remember { mutableStateOf(Offset(800f, 300f)) }
    val dotSize = 16.dp
    val dotSizePx = with(LocalDensity.current) { dotSize.toPx() }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Dot(dotSize, firstPointOffset)
        Dot(dotSize, secondPointOffset)
        Dot(dotSize, thirdPointOffset)
        Dot(dotSize, forthPointOffset)
        Line(dotSizePx, firstPointOffset, secondPointOffset)
        Line(dotSizePx, secondPointOffset, thirdPointOffset)
        Line(dotSizePx, thirdPointOffset, forthPointOffset)
    }
}

@Composable
private fun Dot(size: Dp, offset: MutableState<Offset>) {
    Box(
        modifier = Modifier
            .offset { IntOffset(offset.value.x.roundToInt(), offset.value.y.roundToInt()) }
            .size(size)
            .background(Color.Black, CircleShape)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offset.value += dragAmount
                }
            }
    )
}

@Composable
private fun Line(
    dotSizePx: Float,
    offsetDotA: MutableState<Offset>,
    offsetDotB: MutableState<Offset>
) {
    fun MutableState<Offset>.getDotCenterOffset() = Offset(
        x = value.x + dotSizePx / 2,
        y = value.y + dotSizePx / 2
    )

    val path by remember { mutableStateOf(Path()) }
    val horizontalDelta by remember {
        derivedStateOf {
            abs(offsetDotA.value.x - offsetDotB.value.x) / 2
        }
    }
    val midAnimatedPointOffset = animateOffsetAsState(
        targetValue = Offset(
            x = minOf(offsetDotA.value.x, offsetDotB.value.x) + horizontalDelta + dotSizePx / 2,
            y = maxOf(offsetDotA.value.y, offsetDotB.value.y) + horizontalDelta + dotSizePx / 2
        ),
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = 105f)
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        path.reset()
        path.moveTo(offsetDotA.getDotCenterOffset().x, y = offsetDotA.getDotCenterOffset().y)
        path.quadraticBezierTo(
            x1 = midAnimatedPointOffset.value.x,
            y1 = midAnimatedPointOffset.value.y,
            x2 = offsetDotB.getDotCenterOffset().x,
            y2 = offsetDotB.getDotCenterOffset().y
        )
        drawPath(path = path, color = Color.Black, style = Stroke(width = 5f))
        drawLine(Color.Red, offsetDotA.getDotCenterOffset(), midAnimatedPointOffset.value)
        drawLine(Color.Red, midAnimatedPointOffset.value, offsetDotB.getDotCenterOffset())
        drawCircle(color = Color.Red, radius = 10f, center = Offset(midAnimatedPointOffset.value.x, midAnimatedPointOffset.value.y))
    }

}
