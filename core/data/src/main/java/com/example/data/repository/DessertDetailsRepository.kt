package com.example.data.repository

import android.util.Log
import com.example.data.model.asDessertLookUp
import com.example.model.data.DessertLookUp
import com.example.network.AbpNetworkSource
import com.example.network.model.MealLookUpDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


class DessertDetailsRepository @Inject constructor(
    private val abpNetworkSource: AbpNetworkSource
):DessertLookUpRepository{
    override suspend fun getDessertDetails(id:String): List<DessertLookUp> = abpNetworkSource.getMealLookUp(id).arrMealLookUpDetails.map(MealLookUpDetail::asDessertLookUp)
}