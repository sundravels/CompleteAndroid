package com.example.domain

import com.example.data.repository.DessertsImagesQuery
import com.example.data.repository.OfflineDessertsRepository
import com.example.data.repository.OfflineUserImagesRepository
import com.example.model.data.DessertImages
import com.example.model.data.FavouritesDesserts
import com.example.model.data.mapToUserImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetUserImageUseCase @Inject constructor(
    private val offlineUserImagesRepository: OfflineUserImagesRepository,
    private val offlineImagesRepository: OfflineDessertsRepository
) {

    operator fun invoke(): Flow<HashMap<String, List<DessertImages>>> =
        offlineUserImagesRepository.getImages(DessertsImagesQuery(null))
            .mapToCategories(offlineImagesRepository.getImages())

    operator fun invoke(dessertsImagesQuery: DessertsImagesQuery): Flow<List<DessertImages>> =
        offlineUserImagesRepository.getImages(dessertsImagesQuery)
            .mapData(offlineImagesRepository.getImages())

    private fun Flow<List<DessertImages>>.mapData(images: Flow<FavouritesDesserts>): Flow<List<DessertImages>> =
        combine(images) { dessertImages: List<DessertImages>, favouritesDesserts: FavouritesDesserts ->
            dessertImages.mapToUserImages(favouritesDesserts).distinct()
                .sortedByDescending { it.strMealThumb }
        }

    private fun Flow<List<DessertImages>>.mapToCategories(images: Flow<FavouritesDesserts>): Flow<HashMap<String, List<DessertImages>>> =
        combine(images) { dessertImages: List<DessertImages>, favouritesDesserts: FavouritesDesserts ->
            dessertImages.mapToUserImages(favouritesDesserts).distinct()
                .sortedByDescending { it.strMealThumb }.mapCategories()
        }

    private fun List<DessertImages>.mapCategories(): HashMap<String, List<DessertImages>> {
        val size = (this.size) / 2
        val map = HashMap<String, List<DessertImages>>()
        map["Recommended for you"] = this.subList(0, size)
        map["Highly rated"] = this.subList(size, this.size)
        return map
    }

}