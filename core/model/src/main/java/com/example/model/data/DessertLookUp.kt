package com.example.model.data


data class DessertLookUp(
    val idMeal:String?=null,
    val strInstructions:String?=null,
    val strMealThumb:String?=null,
    val strMeal:String?=null,
    val strCategory:String?=null,
    val strArea:String?=null,
    val isFavourite:Boolean=false,
    val ingredientsList:ArrayList<Pair<String?,String?>> = arrayListOf()
)

