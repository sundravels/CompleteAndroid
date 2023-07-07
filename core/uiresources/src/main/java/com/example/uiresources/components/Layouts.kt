package com.example.uiresources.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.model.data.UserImages
import com.example.uiresources.icon.AppIcons


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageFeed(modifier: Modifier,column:Int,imageList: List<UserImages>,favourite:(String,Boolean)->Unit
){
    LazyVerticalStaggeredGrid(modifier = modifier, columns = StaggeredGridCells.Fixed(column), content={
        items(imageList) {
            homeGridItem(userImages = it, favourite)
        }
    }, verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp))
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyStaggeredGridItemScope.homeGridItem(userImages: UserImages, fn:(String, Boolean)->Unit) {
    Card(elevation = CardDefaults.elevatedCardElevation(), shape = RoundedCornerShape(10.dp)) {
        Box {
            AsyncImage(model = userImages.regularUrl, contentDescription = null)
            Icon(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(3.dp)
                .clickable {
                    fn(userImages.id, !userImages.isFavourite)
                },
                painter = painterResource(id = when(userImages.isFavourite){
                    true -> AppIcons.is_filled_star
                    else -> AppIcons.is_outline_star
                }),
                contentDescription = null, tint = Color.White)
        }
    }
}
