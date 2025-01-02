package com.kunila.ocean.presentation.navigation

sealed class Screens (val route: String) {
    data object SplashScreen : Screens("splash_screen")
    data object TabScreen : Screens("tab_screen")
    data object TemperatureScreen : Screens("temperature_screen")
    data object WindScreen : Screens("wind_screen")
    data object PollutionScreen : Screens("pollution_screen")
    data object AquaScreen : Screens("aqua_screen")
    data object GraphScreen : Screens("graph_screen")
    data object ProductsScreen : Screens("products_screen")
    data object ContactScreen : Screens("contact_screen")
}