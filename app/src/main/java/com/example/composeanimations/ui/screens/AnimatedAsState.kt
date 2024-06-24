package com.example.composeanimations.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
internal fun AnimatedAsStateScreen(modifier: Modifier = Modifier) {
    var active by remember { mutableStateOf(true) }
    val color: State<Color> = animateColorAsState(
        if (active) Color.Red else Color.Blue
    )
    val size = animateDpAsState(
        if (active) 60.dp else 150.dp
    )
    val rotation = animateFloatAsState(
        if (active) 0f else 180f
    )

    LaunchedEffect(active) {
        delay(1000)
        active = !active
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedSquare(
            color,
            size,
            rotation
        )
    }
}

@Composable
private fun AnimatedSquare(color: State<Color>, size: State<Dp>, rotation: State<Float>, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(size.value)
            .background(color.value)
            .graphicsLayer {
                rotationX = rotation.value
                rotationY = rotation.value
                rotationY = rotation.value
            }
    )
}
