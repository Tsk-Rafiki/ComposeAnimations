package com.example.composeanimations.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

sealed interface State {
    object StateGray : State
    object StateGreen : State
    object StateBlue : State
}

@Composable
internal fun UpdateTransitionScreen(modifier: Modifier = Modifier) {
    val grayPointOffset = remember { mutableStateOf(IntOffset(50, 50)) }
    val greenPointOffset = remember { mutableStateOf(IntOffset(250, 225)) }
    val bluePointOffset = remember { mutableStateOf(IntOffset(50, 400)) }

    var currentState: State by remember { mutableStateOf(State.StateGray) }
    val transition = updateTransition(currentState, label = "transition")
    val personOffset by transition.animateIntOffset(
        label = "personOffset",
        transitionSpec = {
            when {
                State.StateGray isTransitioningTo State.StateBlue -> tween(500)
                State.StateBlue isTransitioningTo State.StateGreen -> tween(4000)
                else -> spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioHighBouncy
                )
            }
        }
    ) { targetState ->
        when (targetState) {
            is State.StateGray -> grayPointOffset.value
            is State.StateGreen -> greenPointOffset.value
            is State.StateBlue -> bluePointOffset.value
        }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    currentState = State.StateGray
                },
                content = { Text("To gray\nelement", modifier = Modifier.background(Color.Gray)) }
            )
            Button(
                onClick = {
                    currentState = State.StateGreen
                },
                content = { Text("To green\nelement", modifier = Modifier.background(Color.Green)) }
            )
            Button(
                onClick = {
                    currentState = State.StateBlue
                },
                content = { Text("To blue\nelement", modifier = Modifier.background(Color.Blue)) }
            )
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .offset(grayPointOffset.value.x.dp, grayPointOffset.value.y.dp)
                    .background(Color.Gray, CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .offset(greenPointOffset.value.x.dp, greenPointOffset.value.y.dp)
                    .background(Color.Green, CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .offset(bluePointOffset.value.x.dp, bluePointOffset.value.y.dp)
                    .background(Color.Blue, CircleShape)
            )
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(36.dp)
                    .offset(personOffset.x.dp, personOffset.y.dp)
                    .background(Color.Black)
            )

        }
    }
}
