package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.data.DessertImages

@Entity(tableName = "images")
data class DessertsEntity(
    @ColumnInfo val strMeal: String?,
    @ColumnInfo val strMealThumb: String?,
    @PrimaryKey val id: String,
    @ColumnInfo val ratings:Float,
    @ColumnInfo val likes:Int,
)


fun DessertsEntity.asUserImage() = DessertImages(
    strMeal = this.strMeal,
    strMealThumb = this.strMealThumb,
    idMeal = this.id,
    isFavourite = false,
    ratings=this.ratings,
    likes=this.likes
)