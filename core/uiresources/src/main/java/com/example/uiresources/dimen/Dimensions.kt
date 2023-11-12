package com.example.uiresources.dimen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.example.uiresources.R

object Dimensions {

    val bannerImageHeight
        @Composable get() = dimensionResource(id = R.dimen.detail_image_height)

    val tobAppbar
        @Composable get() = dimensionResource(id = R.dimen.detail_scree_top_app_bar_height)
    val searchBarHeight
       @Composable get() = dimensionResource(id = R.dimen.search_bar_height)
}