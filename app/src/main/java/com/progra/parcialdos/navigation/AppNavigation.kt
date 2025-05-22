package com.progra.parcialdos.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.serialization.encodeToString

import java.net.URLDecoder
import java.net.URLEncoder

import com.progra.parcialdos.plans.PlansUI
import com.progra.parcialdos.dates.DatesUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.DatesScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }

    ) {

        composable(Screen.PlansScreen.route) {
            PlansUI()
        }

        composable(Screen.DatesScreen.route) {
            DatesUI()
        }

    }


}