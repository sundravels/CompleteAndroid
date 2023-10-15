package com.example.data.repository

import com.example.model.data.DessertLookUp
import kotlinx.coroutines.flow.Flow

interface DessertLookUpRepository {
   suspend fun getDessertDetails(id:String): List<DessertLookUp>
}