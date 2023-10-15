package com.example.model.data


//user image data class
data class DessertImages(
     val strMeal: String? ,
     val strMealThumb: String?,
     val idMeal: String?,
     var isFavourite:Boolean = false
)

fun List<DessertImages>.mapToUserImages(listFromDao: List<DessertImages>, userData: UserData) = map {
          DessertImages(strMeal = it.strMeal,
               strMealThumb = it.strMealThumb,
               idMeal = it.idMeal,
               isFavourite = userData.favouriteImagesIds.contains(it.idMeal))
     }






