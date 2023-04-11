package com.example.uiresources.icon

import androidx.annotation.DrawableRes
import com.example.uiresources.R
import androidx.compose.ui.graphics.vector.ImageVector

object AppIcons {
    val ic_home = R.drawable.ic_baseline_home_24
    val ic_favourites = R.drawable.ic_baseline_favorite_24
}

sealed class Icon{
    data class ImageVectors(val imageVector: ImageVector):Icon()
    data class DrawableResource(@DrawableRes val id:Int):Icon()
}