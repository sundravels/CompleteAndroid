package com.example.data.repository

import com.example.model.data.DessertLookUp
import com.example.model.data.UserData
import kotlinx.coroutines.flow.Flow


interface DessertsRepository{

  fun getImages(): Flow<UserData>
  suspend fun addToFavourites(id:String,isFavourites:Boolean)
}