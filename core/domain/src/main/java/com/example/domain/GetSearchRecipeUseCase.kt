package com.example.domain

import android.util.Log
import com.example.data.repository.SearchQuery
import com.example.data.repository.SearchRepository
import com.example.model.data.DessertImages
import com.example.model.data.FavouritesDesserts
import com.example.model.data.mapToUserImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetSearchRecipeUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke(): Flow<List<DessertImages>> = searchRepository.getResults().mapSearchResults(searchRepository.getImages())


    private fun Flow<List<DessertImages>>.mapSearchResults(flow: Flow<FavouritesDesserts>) =
        combine(flow) { desserImages: List<DessertImages>, favourites: FavouritesDesserts ->
            desserImages.mapToUserImages(favourites).distinct().sortedByDescending { it.strMeal }
        }
}