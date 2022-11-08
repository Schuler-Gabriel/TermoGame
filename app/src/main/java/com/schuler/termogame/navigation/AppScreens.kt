package com.schuler.termogame.navigation

enum class AppScreens {
    HomeScreen,
    PlayScreen,
    DefinitionScreen,
    StatsScreen;
    companion object {
        fun fromRoute(route: String?): AppScreens
                = when (route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            PlayScreen.name -> PlayScreen
            StatsScreen.name -> StatsScreen
            DefinitionScreen.name -> DefinitionScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognize")
        }
    }
}