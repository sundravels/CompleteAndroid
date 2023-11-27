package com.example.uiresources.components

sealed class HomeScreenClickHandler{
    class DETAIl_SCREEN(val id:String?):HomeScreenClickHandler()
    class VIEW_ALL_SCREEN(val type:Int):HomeScreenClickHandler()
}