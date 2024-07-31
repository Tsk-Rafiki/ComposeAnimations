package com.example.composeanimations.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.exponentialDecay
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun Animatable(modifier: Modifier = Modifier) {
    val animatable = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            animatable.animateDecay(
                initialVelocity = 1000f,
                animationSpec = exponentialDecay()
            )
            animatable.animateTo(
                targetValue = 0f,
                animationSpec = tween(2000)
            )
        }
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
                .offset(animatable.value.dp)
                .background(Color.Green, CircleShape)
        )
    }
}
