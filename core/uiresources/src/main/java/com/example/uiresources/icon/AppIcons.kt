package com.example.uiresources.icon

import androidx.annotation.DrawableRes
import com.example.uiresources.R
import androidx.compose.ui.graphics.vector.ImageVector

object AppIcons {
    val ic_home = R.drawable.ic_outline_home_24
    val ic_favourites = R.drawable.ic_baseline_favorite_24
    val is_outline_star = R.drawable.ic_baseline_star_border_24
    val is_filled_star = R.drawable.ic_baseline_star_fille_24
    val bg_tempo = androidx.constraintlayout.widget.R.drawable.tooltip_frame_light
}

sealed class Icon{
    data class ImageVectors(val imageVector: ImageVector):Icon()
    data class DrawableResource(@DrawableRes val id:Int):Icon()
}