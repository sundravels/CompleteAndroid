package com.example.uiresources.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.model.data.DessertImages
import com.example.uiresources.icon.AppIcons
import com.example.uiresources.icon.AppIcons.ic_favourites
import com.example.uiresources.icon.AppIcons.ic_ratings_filled
import com.example.uiresources.theme.Gray
import com.example.uiresources.theme.Purple500
import com.example.uiresources.theme.SecondaryShade
import sundravels.samples.utilities.extensions.Empty


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageFeed(
    modifier: Modifier,
    column: Int,
    imageList: List<DessertImages>,
    favourite: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.padding(10.dp), columns = StaggeredGridCells.Fixed(column), content = {
            items(imageList) {
                RecommendedForYou(dessertImages = it, fn = favourite, detailFn = detailFn)
            }
        }, verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecommendedForYou(
    modifier: Modifier = Modifier,
    dessertImages: DessertImages,
    fn: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit
) {

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .clickable {
                detailFn(HomeScreenClickHandler.DETAIl_SCREEN(dessertImages.idMeal))
            }
            .shadow(
                elevation = 5.dp,
                ambientColor = SecondaryShade,
                shape = RoundedCornerShape(10.dp)
            ), colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = modifier) {

            AsyncImage(
                model = dessertImages.strMealThumb,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(verticalGradient(imageOverlayRecommendedForYouColors))
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 10.dp, vertical = 15.dp)
                ) {

                    Text(
                        text = dessertImages.strMeal ?: String.Empty(),
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    ) {

                        Icon(
                            modifier = Modifier
                                .size(12.dp)
                                .clickable {
                                    fn(dessertImages.idMeal ?: "", !dessertImages.isFavourite)
                                },
                            painter = painterResource(
                                id = ic_favourites
                            ),
                            contentDescription = null, tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${dessertImages.likes}K",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Icon(
                            modifier = Modifier
                                .size(12.dp),
                            painter = painterResource(
                                id = ic_ratings_filled
                            ),
                            contentDescription = null, tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${dessertImages.ratings}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color.White,
                            )
                        )

                    }
                }
                Icon(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(horizontal = 10.dp, vertical = 20.dp)
                        .clickable {
                            fn(dessertImages.idMeal ?: "", !dessertImages.isFavourite)
                        }
                        .drawBehind {
                            val radius = 0.7f * size.height
                            drawCircle(color = Color.White, radius = radius)
                        },
                    painter = painterResource(
                        id = when (dessertImages.isFavourite) {
                            true -> AppIcons.ic_favourites
                            else -> AppIcons.ic_favourites_outlined
                        }
                    ),
                    contentDescription = null, tint = MaterialTheme.colorScheme.onBackground
                )
            }


        }
    }
}

@Composable
fun HighlyRated(
    modifier: Modifier = Modifier,
    dessertImages: DessertImages,
    fn: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit,
    isIcon: Boolean = false
) {
    Column(modifier = Modifier.size(width = 200.dp, height = 250.dp)) {

        Box {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .clickable {
                        detailFn(HomeScreenClickHandler.DETAIl_SCREEN(dessertImages.idMeal))
                    }
                    .shadow(
                        elevation = 5.dp,
                        ambientColor = SecondaryShade,
                        shape = RoundedCornerShape(10.dp)
                    ), colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {

                AsyncImage(
                    model = dessertImages.strMealThumb,
                    contentDescription = null,
                    modifier = modifier,
                    contentScale = ContentScale.FillBounds
                )

            }
//            Canvas(modifier = modifier.clip(shape = RoundedCornerShape(10.dp)), onDraw = {
//                drawRect(brush = horizontalGradient(imageOverlayColors))
//            })
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {

            Icon(
                modifier = Modifier
                    .size(12.dp)
                    .clickable {
                        fn(dessertImages.idMeal ?: "", !dessertImages.isFavourite)
                    },
                painter = painterResource(
                    id = ic_favourites
                ),
                contentDescription = null, tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier,
                text = "${dessertImages.likes}K",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier
                    .size(12.dp),
                painter = painterResource(
                    id = ic_ratings_filled
                ),
                contentDescription = null, tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier,
                text = "${dessertImages.ratings}",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                )
            )



            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Icon(
                modifier = Modifier
                    .size(12.dp)
                    .clickable {
                        fn(dessertImages.idMeal ?: "", !dessertImages.isFavourite)
                    }
                    .drawBehind {
                        drawCircle(color = Gray, radius = size.height)
                    },
                painter = painterResource(
                    id = when (dessertImages.isFavourite) {
                        true -> AppIcons.ic_favourites
                        else -> AppIcons.ic_favourites_outlined
                    }
                ),
                contentDescription = null, tint = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = dessertImages.strMeal ?: String.Empty(),
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }


}

@Composable
fun DessertsList(
    dessertImages: DessertImages,
    favourite: (String, Boolean) -> Unit,
    detailFn: (HomeScreenClickHandler) -> Unit
){
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {

            AsyncImage(
                model = dessertImages.strMealThumb,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        detailFn(HomeScreenClickHandler.DETAIl_SCREEN(dessertImages.idMeal))
                    },
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(verticalGradient(imageOverlayRecommendedForYouColors))
            ) {

            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 10.dp)
            ) {
                Column {
                    Card(
                        shape = RoundedCornerShape(50.dp), colors = CardDefaults.cardColors(
                            contentColor = Color.White,
                            containerColor = Color.White
                        ), modifier = Modifier.size(60.dp, 25.dp)
                    ) {

                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = com.example.uiresources.R.drawable.ic_baseline_star_fille_24),
                                contentDescription = null,
                                tint = Color.Black
                            )

                            Text(
                                text = "${dessertImages.ratings}",
                                style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onBackground)
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    dessertImages.strMeal?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                        )
                    }
                }


            }

            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .clickable {
                        favourite(dessertImages.idMeal ?: "", !dessertImages.isFavourite)
                    }
                    .drawBehind {
                        val radius = 0.7f * size.height
                        drawCircle(color = Color.White, radius = radius)
                    },
                painter = painterResource(
                    id = when (dessertImages.isFavourite) {
                        true -> AppIcons.ic_favourites
                        else -> AppIcons.ic_favourites_outlined
                    }
                ),
                contentDescription = null, tint = Purple500
            )

        }

    }
}


