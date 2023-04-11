package com.example.home.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptions


const val homeScreenRoute = "home"

fun NavController.navigateToHomeScreen(navOptions: NavOptions?=null){
    this.navigate(homeScreenRoute,navOptions)
}


