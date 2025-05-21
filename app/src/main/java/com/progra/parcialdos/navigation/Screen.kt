package com.progra.parcialdos.navigation

sealed class Screen(val route: String) {

    object PlansScreen: Screen("plans")
}