package com.example.composeanimations.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun AnimatedContentScreen(modifier: Modifier = Modifier) {
    var state by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedContent(
            state,
            label = "animatedContentExample",
            transitionSpec = {
                slideInHorizontally { -it } + fadeIn() togetherWith
                        slideOutVertically { it } + fadeOut() using SizeTransform(clip = false)
            }
        ) {
            Text(
                text = "Content: $it",
                textAlign = TextAlign.Center,
                modifier = Modifier.defaultMinSize(minHeight = 36.dp).padding(bottom = 24.dp),
            )
        }
        Button(onClick = { state += 1 }) {
            Text(text = "ToggleAnimation")
        }
    }
}
