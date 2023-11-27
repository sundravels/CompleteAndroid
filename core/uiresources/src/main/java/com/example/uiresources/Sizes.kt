package com.example.uiresources

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp


enum class RecipeSize( val size: DpSize) {
    HIGHLY_RATED(DpSize(200.dp, 180.dp)),
    RECOMMENDED_FOR_YOU(DpSize(280.dp, 370.dp))
}