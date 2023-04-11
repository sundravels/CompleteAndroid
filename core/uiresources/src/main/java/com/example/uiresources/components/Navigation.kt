package com.example.uiresources.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun AbpBottomNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    content: @Composable RowScope.() -> Unit
) {

    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = NavigationBarDefaults.Elevation,
        windowInsets = NavigationBarDefaults.windowInsets,
        content = content
    )
}


@Composable
fun RowScope.AbpBottomNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    NavigationBarItem(
        icon = icon,
        label = label,
        selected = selected,
        onClick = onClick,
        colors = colors
    )
}

