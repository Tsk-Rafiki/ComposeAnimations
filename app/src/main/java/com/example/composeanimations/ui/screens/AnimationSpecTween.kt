package com.example.composeanimations.ui.screens

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private const val ANIMATION_DURATION = 500
private const val DELAY_BETWEEN_ANIMATIONS = 1000L

@Composable
internal fun AnimationSpecTween(modifier: Modifier = Modifier) {
    var isStart by remember { mutableStateOf(true) }
    val offsetFOSI = animateFloatAsState(
        targetValue = if (isStart) 0f else 280f,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = FastOutSlowInEasing
        )
    )
    val offsetFOLI = animateFloatAsState(
        targetValue = if (isStart) 0f else 280f,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = FastOutLinearInEasing
        )
    )
    val offsetLinear = animateFloatAsState(
        targetValue = if (isStart) 0f else 280f,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = LinearEasing
        )
    )
    LaunchedEffect(isStart) {
        delay(DELAY_BETWEEN_ANIMATIONS)
        isStart = !isStart
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "FastOutSlowInEasing", modifier = Modifier.padding(vertical = 8.dp))
        Box(
            modifier = Modifier
                .offset(x = offsetFOSI.value.dp)
                .size(48.dp)
                .background(
                    Color.Green,
                    shape = RoundedCornerShape(48.dp)
                )
        )
        Text(text = "FastOutLinearInEasing", modifier = Modifier.padding(vertical = 8.dp))
        Box(
            modifier = Modifier
                .offset(x = offsetFOLI.value.dp)
                .size(48.dp)
                .background(
                    Color.Green,
                    shape = RoundedCornerShape(48.dp)
                )
        )
        Text(text = "LinearEasing", modifier = Modifier.padding(vertical = 8.dp))
        Box(
            modifier = Modifier
                .offset(x = offsetLinear.value.dp)
                .size(48.dp)
                .background(
                    Color.Green,
                    shape = RoundedCornerShape(48.dp)
                )
        )
    }
}
