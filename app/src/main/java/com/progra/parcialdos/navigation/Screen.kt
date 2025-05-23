package com.progra.parcialdos.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object DatesScreen: Screen("dates")
}