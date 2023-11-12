package com.example.uiresources.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.data.DessertImages
import com.example.model.data.DessertLookUp
import com.example.uiresources.dimen.Dimensions
import com.example.uiresources.theme.Purple500



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpTopBar(
    dessertLookUp: DessertLookUp,
    addToFavourites: (String, Boolean) -> Unit,
    isFavourite:Boolean,
    backPress: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .height(Dimensions.tobAppbar)
            .onGloballyPositioned {
                it.positionInWindow()
                Log.v("TAGPosition", "${it.positionInWindow()}")
            }
    ) {

        Image(
            painter = painterResource(id = com.example.uiresources.R.drawable.baseline_arrow_back_24),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    backPress()
                },
            colorFilter = ColorFilter.tint(color = Purple500)
        )

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(
                id = when (isFavourite) {
                    true -> com.example.uiresources.R.drawable.ic_baseline_favorite_24
                    else -> com.example.uiresources.R.drawable.ic_baseline_favorite_border_24
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    dessertLookUp.idMeal?.let {
                        addToFavourites(
                            it, when (isFavourite) {
                                true -> false
                                else -> true
                            }
                        )
                    }
                },
            colorFilter = ColorFilter.tint(color = Purple500)
        )
    }


}
