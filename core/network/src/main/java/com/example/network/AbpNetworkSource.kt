package com.example.network

import com.example.network.model.MealLookUp
import com.example.network.model.Meals

//interface containing all the api calls
interface AbpNetworkSource {
    suspend fun getMeals(): Meals

    suspend fun getMealLookUp(id:String):MealLookUp
}