package com.example.composeanimations.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
internal fun CanvasAnimationDot(modifier: Modifier = Modifier) {
    val firstPointOffset = remember { mutableStateOf(Offset(200f, 300f)) }
    val secondPointOffset = remember { mutableStateOf(Offset(400f, 300f)) }
    val dotSize = 16.dp

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        DotUnOptimized(dotSize, firstPointOffset.value, Modifier.pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                firstPointOffset.value += dragAmount
            }
        })
        Dot(dotSize, secondPointOffset)
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
private fun DotUnOptimized(size: Dp, offset: Offset, modifier: Modifier) {
    Box(
        modifier = Modifier
            .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
            .size(size)
            .background(Color.Gray, CircleShape)
                then modifier
    )
}
