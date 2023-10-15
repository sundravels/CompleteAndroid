package com.example.data.repository

import com.example.data.Synchronizer
import com.example.data.model.asEntity
import com.example.database.dao.ImagesDao
import com.example.database.model.DessertsEntity
import com.example.database.model.asUserImage
import com.example.network.AbpNetworkSource
import com.example.network.model.MealsDetails
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineUserImagesRepository @Inject constructor(
    private val imagesDao: ImagesDao,
    private val abpNetworkSource: AbpNetworkSource
) : UserDataRepository {
    override  fun getImages(imagesQuery: ImagesQuery) = imagesDao.getAllImages(imagesQuery.bookmarkedIds?.isNotEmpty()?:false,imagesQuery.bookmarkedIds?: emptySet()).map {
        it.map(DessertsEntity::asUserImage)
    }
    override suspend fun syncWith(synchronizer: Synchronizer) = kotlin.runCatching {
        val imageResource = abpNetworkSource.getMeals()
        imageResource.arrMealDetails.map(MealsDetails::asEntity).forEach {
            imagesDao.insert(it)
        }
    }.isSuccess
}


