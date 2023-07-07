package com.example.database.dao

import androidx.room.*
import com.example.database.model.ImagesEntity
import com.example.model.data.UserImages
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {
    @Insert
    fun insertAll(vararg imagesEntity: ImagesEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(imagesEntity: ImagesEntity)

    @Query("SELECT * FROM images WHERE CASE WHEN :favouriteIds THEN id in (:favouritesList) ELSE 1 END")
    fun getAllImages(favouriteIds:Boolean = false,
                     favouritesList:Set<String>): Flow<List<ImagesEntity>>
}