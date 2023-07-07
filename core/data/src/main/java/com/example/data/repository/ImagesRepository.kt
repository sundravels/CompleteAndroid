package com.example.data.repository

import com.example.data.Syncable
import com.example.model.data.UserData
import com.example.model.data.UserImages
import java.util.concurrent.Flow


interface ImagesRepository{

  fun getImages(): kotlinx.coroutines.flow.Flow<UserData>

  suspend fun addToFavourites(id:String,isFavourites:Boolean)
}