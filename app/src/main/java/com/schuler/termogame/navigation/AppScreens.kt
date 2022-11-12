package com.schuler.termogame.navigation

enum class AppScreens {
    SplashScreen,
    HomeScreen,
    PlayScreen,
    DefinitionScreen,
    StatsScreen;
    companion object {
        fun fromRoute(route: String?): AppScreens
                = when (route?.substringBefore("/")){
            SplashScreen.name -> SplashScreen
            HomeScreen.name -> HomeScreen
            PlayScreen.name -> PlayScreen
            StatsScreen.name -> StatsScreen
            DefinitionScreen.name -> DefinitionScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognize")
        }
    }
}