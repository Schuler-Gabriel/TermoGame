package com.schuler.termogame.navigation


import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.schuler.termogame.screens.definitions.DefinitionScreen
import com.schuler.termogame.screens.play.PlayScreen
import com.schuler.termogame.screens.home.HomeScreen
import com.schuler.termogame.screens.splash.SplashScreen
import com.schuler.termogame.screens.home.HomeViewModel
import com.schuler.termogame.screens.stats.StatsScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(homeViewModel: HomeViewModel = viewModel() ){
    val navController = rememberAnimatedNavController()
    val fadeSpec: FiniteAnimationSpec<Float> = spring(stiffness = Spring.StiffnessVeryLow)
    val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioNoBouncy)

    AnimatedNavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.name
    ){
        composable(
            route = AppScreens.SplashScreen.name,
            enterTransition = {
                slideInVertically(initialOffsetY = { 1000 }, animationSpec = springSpec)
            },
            exitTransition = {
                slideOutVertically(targetOffsetY = { -1000 }, animationSpec = springSpec)
            },
            popEnterTransition = {
                slideInVertically(initialOffsetY = { -1000 }, animationSpec = springSpec)
            },
            popExitTransition = {
                slideOutVertically(targetOffsetY = { 1000 }, animationSpec = springSpec)
            }
        ){
            SplashScreen(
                navController = navController
            )
        }
        composable(
            route = AppScreens.HomeScreen.name,
            enterTransition = {
                slideInVertically(initialOffsetY = { 1000 }, animationSpec = springSpec)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = springSpec)
            }
        ){
            HomeScreen(
                homeViewModel,
                navController = navController
            )
        }
        composable(
            route = AppScreens.PlayScreen.name,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSpec)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = springSpec)
            }
        ){
            PlayScreen(
                homeViewModel,
                navController = navController
            )
        }
        composable(
            route = AppScreens.DefinitionScreen.name,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSpec)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = springSpec)
            }
        ){
            DefinitionScreen(
                homeViewModel,
                navController = navController
            )
        }
        composable(
            route = AppScreens.StatsScreen.name,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = springSpec)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = springSpec)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = springSpec)
            }
        ){
            StatsScreen(
                homeViewModel,
                navController = navController
            )
        }
    }
}