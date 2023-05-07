package com.example.data.repository

import com.example.data.Syncable
import com.example.model.data.UserImages


interface ImagesRepository:Syncable{

  fun getImages():kotlinx.coroutines.flow.Flow<List<UserImages>>
}