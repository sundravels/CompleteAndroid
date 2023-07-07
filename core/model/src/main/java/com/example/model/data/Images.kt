package com.example.model.data

import com.example.uiresources.icon.AppIcons
import com.google.gson.annotations.SerializedName


//user image data class
data class UserImages(
     val id:String,
     val name:String?,
     val fullUrl:String?,
     val regularUrl:String?,
     val smallUrl:String?,
     var isFavourite:Boolean = false
)

fun List<UserImages>.mapToUserImages(listFromDao: List<UserImages>,userData: UserData) = map {
          UserImages(id = it.id,
               name = it.name,
               fullUrl = it.fullUrl,
               regularUrl = it.regularUrl,
               smallUrl = it.smallUrl,
               isFavourite = userData.favouriteImagesIds.contains(it.id))
     }






