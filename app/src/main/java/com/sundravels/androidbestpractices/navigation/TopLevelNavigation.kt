package com.sundravels.androidbestpractices.navigation


import com.example.favourite.navigation.favourite_screen_route
import com.example.home.navigation.homeScreenRoute
import com.example.uiresources.icon.AppIcons
import com.example.uiresources.icon.Icon
import com.sundravels.androidbestpractices.R


enum class
TopLevelNavigation(val text: Int, val icon: Icon, val route: String,val title:Int) {

    HOME(
        text = R.string.str_home,
        icon = Icon.DrawableResource(AppIcons.ic_home),
        route = homeScreenRoute,
        title = R.string.app_name
    ),
    FAVOURITES(
        text = R.string.str_favourites,
        icon = Icon.DrawableResource(AppIcons.ic_favourites),
        route = favourite_screen_route,
        title = R.string.str_favourites
    )
}

