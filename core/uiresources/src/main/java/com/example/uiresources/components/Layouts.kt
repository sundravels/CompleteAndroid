package com.example.uiresources.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.model.data.DessertImages
import com.example.uiresources.icon.AppIcons
import sundravels.samples.utilities.extensions.Empty


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
        elevation = CardDefaults.elevatedCardElevation(),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.clickable {
            detailFn(dessertImages.idMeal)
        }) {
        Box {
            AsyncImage(model = dessertImages.strMealThumb, contentDescription = null)
            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(3.dp)
                    .clickable {
                        fn(dessertImages.idMeal ?: "", !dessertImages.isFavourite)
                    },
                painter = painterResource(
                    id = when (dessertImages.isFavourite) {
                        true -> AppIcons.is_filled_star
                        else -> AppIcons.is_outline_star
                    }
                ),
                contentDescription = null, tint = Color.White
            )
        }
    }
}


@Composable
fun IngredientsList(
    arrayList: ArrayList<Pair<String?, String?>>,
    paddingValues: PaddingValues,
    strInstructions: String?,
    tabState: Int = 0
) {
    LazyColumn(content = {
        if (tabState == 0) {
            items(arrayList) {
                Row(modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)) {
                    Text(
                        text = it.first ?: String.Empty(),
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    Text(
                        text = it.second ?: String.Empty(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        } else items(1) {
            Instructions(strInstructions = strInstructions)
        }
    }, contentPadding = paddingValues)
}

@Composable
fun Instructions(strInstructions: String?) {
    Text(
        text = strInstructions ?: "",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
}

