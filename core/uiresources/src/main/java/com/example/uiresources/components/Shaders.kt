package com.example.uiresources.components

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


val imageOverlayColors = listOf(Color.Black.copy(0.5f),Color.Black.copy(0.5f))
val imageOverlayRecommendedForYouColors = listOf(Color.Transparent,Color.Black.copy(0.5f))

val horizontalGradient:(colors:List<Color>)->Brush = {
    Brush.horizontalGradient(colors = it)
}


val verticalGradient:(colors:List<Color>)->Brush = {
    Brush.verticalGradient(colors = it)
}