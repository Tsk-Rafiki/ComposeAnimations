package com.example.composeanimations.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.composeanimations.R
import kotlinx.coroutines.delay

@Composable
internal fun TransitionsExampleScreen(modifier: Modifier = Modifier) {
    val transitions = listOfTransition()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(transitions.size) {
            EnterTransitionView(transitions[it])
        }
    }
}

@Composable
private fun EnterTransitionView(item: TransitionExample) {
    var isVisible by remember { mutableStateOf(true) }
    LaunchedEffect(isVisible) {
        delay(1000)
        isVisible = !isVisible
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.name,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .size(128.dp)
                .border(BorderStroke(1.dp, Color.Black), MaterialTheme.shapes.medium)
                .clickable {
                    isVisible = !isVisible
                }
        ) {
            Column {
                AnimatedVisibility(
                    visible = isVisible,
                    modifier = Modifier,
                    enter = item.enterTransition,
                    exit = item.exitTransition,
                    label = "AnimatedVisibility",
                    content = {
                        Image(painter = painterResource(R.drawable.ic_launcher_foreground), null)
                    }
                )
            }
        }
    }
}

class TransitionExample(
    val name: String,
    val enterTransition: EnterTransition,
    val exitTransition: ExitTransition
)

private fun listOfTransition(): List<TransitionExample> = buildList {
    add(TransitionExample("fadeIn\nfadeOut", fadeIn(), fadeOut()))
    add(
        TransitionExample(
            "slideIn\nslideOut",
            slideIn { IntOffset(it.height / 2, it.width / 2) },
            slideOut { IntOffset(it.height / 2, it.width / 2) })
    )
    add(TransitionExample("slideInHorizontally\nslideOutHorizontally", slideInHorizontally(), slideOutHorizontally()))
    add(TransitionExample("expandVertically\nshrinkVertically", expandVertically(), shrinkVertically()))
    add(TransitionExample("expandIn\nshrinkOut", expandIn(), shrinkOut()))
    add(TransitionExample("expandHorizontally\nshrinkHorizontally", expandHorizontally(), shrinkHorizontally()))
    add(TransitionExample("scaleIn\nscaleOut", scaleIn(), scaleOut()))
}
