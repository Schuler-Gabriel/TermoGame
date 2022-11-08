package com.schuler.termogame.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.schuler.termogame.screens.definitions.DefinitionScreen
import com.schuler.termogame.screens.play.PlayScreen
import com.schuler.termogame.screens.home.HomeScreen
import com.schuler.termogame.screens.home.HomeViewModel
import com.schuler.termogame.screens.stats.StatsScreen


@Composable
fun AppNavigation(homeViewModel: HomeViewModel = viewModel() ){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.name
    ){
        composable(route = AppScreens.HomeScreen.name){
            HomeScreen(

                navController = navController
            )
        }
        composable(route = AppScreens.PlayScreen.name){
            PlayScreen(
                homeViewModel,
                navController = navController
            )
        }
        composable(route = AppScreens.DefinitionScreen.name){
            DefinitionScreen(
                homeViewModel,
                navController = navController
            )
        }
        composable(route = AppScreens.StatsScreen.name){
            StatsScreen(
                navController = navController
            )
        }
    }
}