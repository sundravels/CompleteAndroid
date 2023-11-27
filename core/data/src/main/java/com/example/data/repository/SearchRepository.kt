package com.example.data.repository

import android.util.Log
import com.example.database.dao.ImagesDao
import com.example.database.model.DessertsEntity
import com.example.database.model.asUserImage
import com.example.model.data.DessertImages
import com.example.model.data.FavouritesDesserts
import com.sundravels.androidbestpractices.data.UserPreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val imagesDao: ImagesDao,
private val dataSource: UserPreferenceDataSource):SearchRecipeRepository {

    override fun getResults(): Flow<List<DessertImages>>  = imagesDao.getAllImages().map {
        it.map(DessertsEntity::asUserImage)
    }

    override suspend fun addToFavourites(id: String, isFavourites: Boolean) {
        dataSource.setFavourites(id,isFavourites)
    }

    override fun getImages(): Flow<FavouritesDesserts> {
        Log.v("TAGSearchRepository","${dataSource.data.map { 
            Log.v("TAGG","${it}")
        }}")
       return dataSource.data
    }


}