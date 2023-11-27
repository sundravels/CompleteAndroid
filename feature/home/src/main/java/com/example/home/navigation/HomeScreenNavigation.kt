package com.example.home.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptions


const val homeScreenRoute = "home"
const val seeAllScreenRoute ="see_all_screen"
const val seeAllType ="see_all_screen_type"

fun NavController.navigateToHomeScreen(navOptions: NavOptions?=null){
    this.navigate(homeScreenRoute,navOptions)
}
fun NavController.navigateToSeeAllScreen(navOptions: NavOptions?=null,seeAllScreenType: String){
    this.navigate("${seeAllScreenRoute}/${seeAllScreenType}",navOptions)
}

enum class SeeAllScreenType(val type:String){
    RECOMMENDED_FOR_YOU("Recommended for you"),
    HIGHLY_RATED("Highly rated")
}


