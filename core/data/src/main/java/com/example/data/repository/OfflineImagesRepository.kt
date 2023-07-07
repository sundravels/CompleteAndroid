package com.example.data.repository

import com.example.model.data.UserData
import com.sundravels.androidbestpractices.data.UserPreferenceDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineImagesRepository @Inject constructor(
    private val userPreferenceDataStore:UserPreferenceDataSource
):ImagesRepository{
    override fun getImages(): Flow<UserData> = userPreferenceDataStore.data
    override suspend fun addToFavourites(id: String, isFavourites: Boolean) {
            userPreferenceDataStore.setFavourites(id,isFavourites)
    }
}