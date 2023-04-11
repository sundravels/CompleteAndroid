package com.sundravels.androidbestpractices.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.favourite.navigation.favourite_screen_route
import com.example.favourite.navigation.navigateToFavourite
import com.example.home.navigation.homeScreenRoute
import com.example.home.navigation.navigateToHomeScreen
import com.sundravels.androidbestpractices.navigation.TopLevelNavigation


@Stable
class AbpAppState( val navController: NavHostController) {


    val currentDestination
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    val destination
        @Composable get() = when (currentDestination?.route) {
            homeScreenRoute -> TopLevelNavigation.HOME
            favourite_screen_route -> TopLevelNavigation.FAVOURITES
            else -> null
        }

    fun navigate(topLevelNavigation: TopLevelNavigation) {

        val topLevelOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelNavigation) {
            TopLevelNavigation.HOME -> navController.navigateToHomeScreen(topLevelOptions)
            TopLevelNavigation.FAVOURITES -> navController.navigateToFavourite(topLevelOptions)
        }
    }

}


@Composable
fun rememberNiaAppState(navController: NavHostController = rememberNavController()):AbpAppState{
    return AbpAppState(navController)
}