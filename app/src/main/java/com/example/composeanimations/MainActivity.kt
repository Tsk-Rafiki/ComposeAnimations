package com.example.composeanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeanimations.ui.routes.Routes
import com.example.composeanimations.ui.screens.AnimatedAsStateScreen
import com.example.composeanimations.ui.screens.AnimatedContentScreen
import com.example.composeanimations.ui.screens.AnimatedVisibilityScreen
import com.example.composeanimations.ui.screens.CanvasAnimationScreen
import com.example.composeanimations.ui.screens.MainScreen
import com.example.composeanimations.ui.screens.TransitionsExampleScreen
import com.example.composeanimations.ui.screens.UpdateTransitionScreen
import com.example.composeanimations.ui.theme.ComposeAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAnimationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.AnimationList.name,
                        modifier = Modifier.padding(
                            top = paddingValues.calculateTopPadding(),
                            bottom = paddingValues.calculateBottomPadding()
                        )
                    ) {
                        composable(Routes.AnimationList.name) {
                            MainScreen(
                                modifier = Modifier,
                                openAnimationVisibilityScreen = {
                                    navController.navigate(Routes.AnimatedVisibility.name)
                                },
                                openTransitionExamplesScreen = {
                                    navController.navigate(Routes.TransitionExample.name)
                                },
                                openAnimationContentScreen = {
                                    navController.navigate(Routes.AnimatedContent.name)
                                },
                                openAnimationAsStateScreen = {
                                    navController.navigate(Routes.AnimateAsState.name)
                                },
                                openUpdateTransitionScreen = {
                                    navController.navigate(Routes.UpdateTransition.name)
                                },
                                openCanvasAnimationScreen = {
                                    navController.navigate(Routes.CanvasAnimation.name)
                                }
                            )
                        }
                        composable(Routes.AnimatedVisibility.name) {
                            AnimatedVisibilityScreen()
                        }
                        composable(Routes.TransitionExample.name) {
                            TransitionsExampleScreen()
                        }
                        composable(Routes.AnimatedContent.name) {
                            AnimatedContentScreen()
                        }
                        composable(Routes.AnimateAsState.name) {
                            AnimatedAsStateScreen()
                        }
                        composable(Routes.TransitionExample.name) {
                            UpdateTransitionScreen()
                        }
                        composable(Routes.CanvasAnimation.name) {
                            CanvasAnimationScreen()
                        }
                    }
                }
            }
        }
    }
}
