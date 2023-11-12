package com.openglsample.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val detailScreenRoute = "detail_screen_route"

const val detailScreenMealId = "meal_id"

const val isFavourite = "is_favourited"

fun NavController.navigateToDetailScreen(navOptions: NavOptions?=null,meal_id:String?=""){
    this.navigate("${detailScreenRoute}/${meal_id}",navOptions)
}