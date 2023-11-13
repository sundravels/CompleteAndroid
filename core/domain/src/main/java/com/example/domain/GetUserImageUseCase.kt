package com.example.domain

import com.example.data.repository.DessertsImagesQuery
import com.example.data.repository.OfflineDessertsRepository
import com.example.data.repository.OfflineUserImagesRepository
import com.example.model.data.FavouritesDesserts
import com.example.model.data.DessertImages
import com.example.model.data.mapToUserImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetUserImageUseCase @Inject constructor (
    private val offlineUserImagesRepository: OfflineUserImagesRepository,
    private val offlineImagesRepository: OfflineDessertsRepository
) {

    operator fun invoke(dessertsImagesQuery: DessertsImagesQuery):Flow<List<DessertImages>> = offlineUserImagesRepository.getImages(dessertsImagesQuery).mapData(offlineImagesRepository.getImages())

    private fun Flow<List<DessertImages>>.mapData(images: Flow<FavouritesDesserts>):Flow<List<DessertImages>> = combine(images) { dessertImages: List<DessertImages>, favouritesDesserts: FavouritesDesserts ->
        dessertImages.mapToUserImages(dessertImages, favouritesDesserts)
    }

}