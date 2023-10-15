package com.example.network.retrofit

import com.example.network.model.MealLookUp
import com.example.network.model.Meals
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitAbpNetworkApiInterface {
    @GET("api/json/v1/1/filter.php?c=Dessert")
    suspend fun getImages(): Meals

    @GET("api/json/v1/1/lookup.php")
    suspend fun getDetails(@Query(value = "i",encoded = true)mealId:String):MealLookUp

}
