package com.example.network.retrofit

import com.example.network.AbpNetworkSource
import com.example.network.model.MealLookUp
import com.example.network.model.Meals
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitAbpNetwork @Inject constructor(private val retrofit: Retrofit):AbpNetworkSource {

    private val retrofitAbpNetworkApiInterface = retrofit.create(RetrofitAbpNetworkApiInterface::class.java)

    override suspend fun getMeals(): Meals = retrofitAbpNetworkApiInterface.getImages()

    override suspend fun getMealLookUp(id:String):MealLookUp = retrofitAbpNetworkApiInterface.getDetails(id)

}