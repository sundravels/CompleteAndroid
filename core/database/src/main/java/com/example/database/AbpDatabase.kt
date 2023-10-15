package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.ImagesDao
import com.example.database.model.DessertsEntity


@Database(entities = [DessertsEntity::class], version = 1)
abstract class AbpDatabase:RoomDatabase(){
    abstract fun imagesDao():ImagesDao
}