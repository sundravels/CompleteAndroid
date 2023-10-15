package com.example.uiresources.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uiresources.dimen.Dimensions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {

    CenterAlignedTopAppBar(
        modifier = Modifier,
        title = {
            Text(
                "", maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp
                )
            )
        },
        actions = {

        },
        navigationIcon = {}

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpTopBar(toolbarTitle: String, modifier: Modifier) {
    Row(
        modifier = Modifier
            .background(color = Color.Black.copy(alpha = 0.5f))
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .height(Dimensions.tobAppbar)
    ) {

        Image(
            painter = painterResource(id = com.example.uiresources.R.drawable.baseline_arrow_back_24),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterVertically),
            colorFilter = ColorFilter.tint(color = Color.White)
        )
        Text(
            text = toolbarTitle,
            maxLines = 1,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.White,
                fontSize = 20.sp
            ),
            modifier = Modifier
                .padding(start = 40.dp)
                .fillMaxWidth()
                .weight(1f)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Start
        )

        Image(
            painter = painterResource(id = com.example.uiresources.R.drawable.ic_baseline_favorite_24),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterVertically),
            colorFilter = ColorFilter.tint(color = Color.White)
        )
    }


}
