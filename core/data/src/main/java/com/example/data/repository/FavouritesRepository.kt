package com.example.data.repository

import com.example.model.data.FavouritesDesserts
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun addToFavourites(id:String,isFavourites:Boolean)
    fun getImages(): Flow<FavouritesDesserts>
}

