package com.example.model.data


data class DessertLookUp(
    val strInstructions:String?=null,
    val strMealThumb:String?=null,
    val strMeal:String?=null,
    val ingredientsList:ArrayList<Pair<String?,String?>> = arrayListOf()
)

