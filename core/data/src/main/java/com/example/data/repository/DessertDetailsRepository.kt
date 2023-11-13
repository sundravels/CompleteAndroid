package com.example.data.repository

import com.example.data.model.asDessertLookUp
import com.example.model.data.DessertLookUp
import com.example.network.AbpNetworkSource
import com.example.network.model.MealLookUpDetail
import javax.inject.Inject


class DessertDetailsRepository @Inject constructor(
    private val abpNetworkSource: AbpNetworkSource,
) : DessertLookUpRepository {
    override suspend fun getDessertDetails(id: String): List<DessertLookUp> =
        abpNetworkSource.getMealLookUp(id).arrMealLookUpDetails.map(MealLookUpDetail::asDessertLookUp)

}