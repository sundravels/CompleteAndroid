package com.example.database.dao

import androidx.room.*
import com.example.database.model.ImagesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {
    @Query("SELECT * FROM images")
    fun getAllImages(): Flow<List<ImagesEntity>>

    @Insert
    fun insertAll(vararg imagesEntity: ImagesEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(imagesEntity: ImagesEntity)
}