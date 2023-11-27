package com.example.data.repository

import com.example.model.data.DessertImages
import kotlinx.coroutines.flow.Flow

interface SearchRecipeRepository:FavouritesRepository {
    fun getResults(): Flow<List<DessertImages>>
}