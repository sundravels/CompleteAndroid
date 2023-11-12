package com.example.uiresources.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.model.data.DessertImages
import com.example.uiresources.icon.AppIcons
import com.example.uiresources.theme.SecondaryShade


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageFeed(
    modifier: Modifier,
    column: Int,
    imageList: List<DessertImages>,
    favourite: (String, Boolean) -> Unit,
    detailFn: (mealId: String?) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.padding(10.dp), columns = StaggeredGridCells.Fixed(column), content = {
            items(imageList) {
                homeGridItem(dessertImages = it, fn = favourite, detailFn = detailFn)
            }
        }, verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyStaggeredGridItemScope.homeGridItem(
    dessertImages: DessertImages,
    fn: (String, Boolean) -> Unit,
    detailFn: (mealId: String?) -> Unit
) {

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .clickable {
                detailFn(dessertImages.idMeal)
            }
            .shadow(
                elevation = 5.dp,
                ambientColor = SecondaryShade,
                shape = RoundedCornerShape(10.dp)
            ), colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {

            AsyncImage(
                model = dessertImages.strMealThumb,
                contentDescription = null
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(5.dp)
                    .clickable {
                        fn(dessertImages.idMeal ?: "", !dessertImages.isFavourite)
                    },
                painter = painterResource(
                    id = when (dessertImages.isFavourite) {
                        true -> AppIcons.ic_favourites
                        else -> AppIcons.ic_favourites_outlined
                    }
                ),
                contentDescription = null, tint = Color.White
            )

        }
    }
}


