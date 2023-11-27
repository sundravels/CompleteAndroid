package com.example.model.data


//user image data class
data class DessertImages(
     val strMeal: String? ,
     val strMealThumb: String?,
     val idMeal: String?,
     var isFavourite:Boolean = false,
     val ratings:Float,
     val likes:Int
)

fun List<DessertImages>.mapToUserImages(favouritesDesserts: FavouritesDesserts) = map {
          DessertImages(strMeal = it.strMeal,
               strMealThumb = it.strMealThumb,
               idMeal = it.idMeal,
               isFavourite = favouritesDesserts.favouriteImagesIds.contains(it.idMeal),
          ratings = it.ratings,
          likes = it.likes)
     }








