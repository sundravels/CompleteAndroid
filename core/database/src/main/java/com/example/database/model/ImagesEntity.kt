package com.example.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.data.UserImages

@Entity(tableName = "images")
data class ImagesEntity(
    @PrimaryKey val id:String,
    @ColumnInfo val name:String?,
    @ColumnInfo val fullUrl:String?,
    @ColumnInfo val regularUrl:String?,
    @ColumnInfo val smallUrl:String?
)


fun ImagesEntity.asUserImage() = UserImages(
    id = this.id,
    name = this.name,
    fullUrl = this.fullUrl,
    regularUrl = this.regularUrl,
    smallUrl = this.smallUrl,
)