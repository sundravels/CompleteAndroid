package com.example.uiresources.components

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpTopAppBar(title: @Composable () -> Unit,
                 modifier: Modifier = Modifier,
                 navigationIcon: @Composable () -> Unit = {},
                 actions: @Composable RowScope.() -> Unit = {},
                 windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
                 colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
                 scrollBehavior: TopAppBarScrollBehavior? = null){

    CenterAlignedTopAppBar(
        modifier = Modifier,
        title = {
            Text(
               "", maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(color = Color.Black, fontFamily = FontFamily.SansSerif, fontSize = 16.sp)
            )
        },
        actions = {

        },
        navigationIcon = {}

    )
}
