package com.example.database.dao

import androidx.room.*
import com.example.database.model.DessertsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {
    @Insert
    fun insertAll(vararg dessertsEntity: DessertsEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dessertsEntity: DessertsEntity)

    @Query("SELECT * FROM images WHERE CASE WHEN :favouriteIds THEN id in (:favouritesList) ELSE 1 END")
    fun getAllImages(favouriteIds:Boolean = false,
                     favouritesList:Set<String>): Flow<List<DessertsEntity>>

    @Query("SELECT * FROM images")
    fun getAllImages():Flow<List<DessertsEntity>>
}