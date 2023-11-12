package com.example.uiresources.icon

import androidx.annotation.DrawableRes
import com.example.uiresources.R
import androidx.compose.ui.graphics.vector.ImageVector

object AppIcons {
    val ic_home = R.drawable.ic_outline_home_24
    val ic_favourites = R.drawable.ic_baseline_favorite_24
    val ic_favourites_outlined = R.drawable.ic_baseline_favorite_border_24
    val ic_settings = R.drawable.baseline_settings_24
    val bg_tempo = androidx.constraintlayout.widget.R.drawable.tooltip_frame_light
    val ic_menu_type = R.drawable.ic_recipe_type
}

sealed class Icon{
    data class ImageVectors(val imageVector: ImageVector):Icon()
    data class DrawableResource(@DrawableRes val id:Int):Icon()
}