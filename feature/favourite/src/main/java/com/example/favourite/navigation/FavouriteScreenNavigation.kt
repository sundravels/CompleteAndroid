package com.example.favourite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions


const val favourite_screen_route = "favourites"


fun NavController.navigateToFavourite(navOptions: NavOptions?=null){
    this.navigate(favourite_screen_route,navOptions)
}