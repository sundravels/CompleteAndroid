package com.example.data.repository

import com.example.model.data.FavouritesDesserts
import com.sundravels.androidbestpractices.data.UserPreferenceDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineDessertsRepository @Inject constructor(
    private val userPreferenceDataStore:UserPreferenceDataSource
):DessertsRepository{
    override fun getImages(): Flow<FavouritesDesserts> = userPreferenceDataStore.data
    override suspend fun addToFavourites(id: String, isFavourites: Boolean) {
            userPreferenceDataStore.setFavourites(id,isFavourites)
    }
}