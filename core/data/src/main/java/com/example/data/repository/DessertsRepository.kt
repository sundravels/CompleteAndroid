package com.example.data.repository

import com.example.model.data.FavouritesDesserts
import kotlinx.coroutines.flow.Flow


interface DessertsRepository{

  fun getImages(): Flow<FavouritesDesserts>
  suspend fun addToFavourites(id:String,isFavourites:Boolean)
}