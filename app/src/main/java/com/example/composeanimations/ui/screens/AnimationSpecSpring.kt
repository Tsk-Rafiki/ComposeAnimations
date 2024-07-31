package com.example.composeanimations.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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

private const val DELAY_BETWEEN_ANIMATIONS = 1000L

@Composable
internal fun AnimationSpecSpring(modifier: Modifier = Modifier) {
    var isStart by remember { mutableStateOf(true) }
    val noBouncyStiffnesLow = animateFloatAsState(
        targetValue = if (isStart) 0f else 280f,
        animationSpec = spring(
            stiffness = Spring.StiffnessVeryLow,
            dampingRatio = Spring.DampingRatioNoBouncy,
        )
    )
    val noBouncyStiffnesLHigh = animateFloatAsState(
        targetValue = if (isStart) 0f else 280f,
        animationSpec = spring(
            stiffness = Spring.StiffnessHigh,
            dampingRatio = Spring.DampingRatioNoBouncy,
        )
    )
    val lowBouncy = animateFloatAsState(
        targetValue = if (isStart) 0f else 280f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy
        )
    )
    val mediumBouncy = animateFloatAsState(
        targetValue = if (isStart) 0f else 280f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    val highBouncy = animateFloatAsState(
        targetValue = if (isStart) 0f else 280f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy
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
        Text(
            text = "DampingRatioNoBouncy && StiffnessVeryLow",
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(
            modifier = Modifier
                .offset(x = noBouncyStiffnesLow.value.dp)
                .size(48.dp)
                .background(
                    Color.Green,
                    shape = RoundedCornerShape(48.dp)
                )
        )
        Text(
            text = "DampingRatioNoBouncy && StiffnessHigh",
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Box(
            modifier = Modifier
                .offset(x = noBouncyStiffnesLHigh.value.dp)
                .size(48.dp)
                .background(
                    Color.Green,
                    shape = RoundedCornerShape(48.dp)
                )
        )
        Text(text = "DampingRatioLowBouncy", modifier = Modifier.padding(vertical = 8.dp))
        Box(
            modifier = Modifier
                .offset(x = lowBouncy.value.dp)
                .size(48.dp)
                .background(
                    Color.Green,
                    shape = RoundedCornerShape(48.dp)
                )
        )
        Text(text = "DampingRatioMediumBouncy", modifier = Modifier.padding(vertical = 8.dp))
        Box(
            modifier = Modifier
                .offset(x = mediumBouncy.value.dp)
                .size(48.dp)
                .background(
                    Color.Green,
                    shape = RoundedCornerShape(48.dp)
                )
        )
        Text(text = "DampingRatioHighBouncy", modifier = Modifier.padding(vertical = 8.dp))
        Box(
            modifier = Modifier
                .offset(x = highBouncy.value.dp)
                .size(48.dp)
                .background(
                    Color.Green,
                    shape = RoundedCornerShape(48.dp)
                )
        )
    }
}
