package com.example.composeanimations.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun AnimatedVisibilityScreen(modifier: Modifier = Modifier) {
    var isVisible by remember { mutableStateOf(true) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(visible = isVisible) {
            Text(
                text = "Animated Text",
                textAlign = TextAlign.Center,
                modifier = Modifier.defaultMinSize(minHeight = 36.dp).padding(bottom = 24.dp),
            )
        }
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "ToggleAnimation")
        }
    }
}
