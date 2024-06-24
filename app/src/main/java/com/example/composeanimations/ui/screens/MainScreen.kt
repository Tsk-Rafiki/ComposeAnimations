package com.example.composeanimations.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    openAnimationVisibilityScreen: () -> Unit,
    openTransitionExamplesScreen: () -> Unit,
    openAnimationContentScreen: () -> Unit,
    openAnimationAsStateScreen: () -> Unit,
    openUpdateTransitionScreen: () -> Unit,
    openCanvasAnimationScreen: () -> Unit,
) {
    Column(
        modifier = modifier.padding(top = 48.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimationItem("Transition Examples") { openTransitionExamplesScreen() }
        AnimationItem("AnimatedVisibility") { openAnimationVisibilityScreen() }
        AnimationItem("AnimatedContent") { openAnimationContentScreen() }
        AnimationItem("AnimatedAsState") { openAnimationAsStateScreen() }
        AnimationItem("UpdateTransition") { openUpdateTransitionScreen() }
        AnimationItem("CanvasAnimation") { openCanvasAnimationScreen() }
    }
}

@Composable
private fun AnimationItem(title: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Text(
        text = title,
        modifier = modifier.defaultMinSize(minHeight = 36.dp).clickable { onClick() },
    )
}
