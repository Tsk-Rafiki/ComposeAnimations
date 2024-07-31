package com.example.composeanimations.ui.screens

import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun Animation(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf(false) }
    val animation = remember {
        TargetBasedAnimation(
            animationSpec = tween(durationMillis = 2000, delayMillis = 1000),
            typeConverter = Float.VectorConverter,
            initialValue = 100f,
            targetValue = 300f
        )
    }
    var animationDuration by remember { mutableLongStateOf(0L) }
    var animationValue by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(state) {
        val startTime = withFrameNanos { it }
        do {
            animationDuration = withFrameNanos { it } - startTime
            animationValue = animation.getValueFromNanos(animationDuration)
        } while (!animation.isFinishedFromNanos(animationDuration))
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .offset(animationValue.dp)
                .background(Color.Green, CircleShape)
        )
    }
}
