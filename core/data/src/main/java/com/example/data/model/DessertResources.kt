package com.example.data.model

import com.example.database.model.DessertsEntity
import com.example.model.data.DessertLookUp
import com.example.model.data.UserData
import com.example.network.model.MealLookUpDetail
import com.example.network.model.MealsDetails


fun MealsDetails.asEntity() = DessertsEntity(
    id = this.idMeal ?: "",
    strMeal = this.strMeal,
    strMealThumb = this.strMealThumb,

    )

fun MealLookUpDetail.asDessertLookUp() = DessertLookUp(
    idMeal = this.idMeal,
    strInstructions = this.strInstructions,
    strMealThumb = this.strMealThumb,
    strMeal = this.strMeal,
    strArea = this.strArea,
    strCategory = this.strCategory,
    ingredientsList = this.getIngredientsList()
)

fun MealLookUpDetail.getIngredientsList(): ArrayList<Pair<String?, String?>> {
    val ingredientsList = ArrayList<Pair<String?, String?>>()
    ingredientsList.add(Pair(this.strIngredient1, this.strMeasure1))
    ingredientsList.add(Pair(this.strIngredient2, this.strMeasure2))
    ingredientsList.add(Pair(this.strIngredient3, this.strMeasure3))
    ingredientsList.add(Pair(this.strIngredient4, this.strMeasure4))
    ingredientsList.add(Pair(this.strIngredient5, this.strMeasure5))
    ingredientsList.add(Pair(this.strIngredient6, this.strMeasure6))
    ingredientsList.add(Pair(this.strIngredient7, this.strMeasure7))
    ingredientsList.add(Pair(this.strIngredient8, this.strMeasure8))
    ingredientsList.add(Pair(this.strIngredient9, this.strMeasure9))
    ingredientsList.add(Pair(this.strIngredient10, this.strMeasure10))
    ingredientsList.add(Pair(this.strIngredient11, this.strMeasure11))
    ingredientsList.add(Pair(this.strIngredient12, this.strMeasure12))
    ingredientsList.add(Pair(this.strIngredient13, this.strMeasure13))
    ingredientsList.add(Pair(this.strIngredient14, this.strMeasure14))
    ingredientsList.add(Pair(this.strIngredient15, this.strMeasure15))
    ingredientsList.add(Pair(this.strIngredient16, this.strMeasure16))
    ingredientsList.add(Pair(this.strIngredient17, this.strMeasure17))
    ingredientsList.add(Pair(this.strIngredient18, this.strMeasure18))
    ingredientsList.add(Pair(this.strIngredient19, this.strMeasure19))
    ingredientsList.add(Pair(this.strIngredient20, this.strMeasure20))
    return ingredientsList.removeEmpty() as ArrayList<Pair<String?, String?>>
}

fun ArrayList<Pair<String?, String?>>.removeEmpty() =
    run { this.filter { !it.first.isNullOrEmpty() && !it.second.isNullOrEmpty() } }


