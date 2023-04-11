package com.sundravels.androidbestpractices.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.favourite.navigation.favourite_screen_route
import com.example.favourite.ui.FavouriteScreen
import com.example.home.HomeScreenRoute
import com.example.home.navigation.homeScreenRoute
import com.example.uiresources.components.AbpBottomNavigationBar
import com.example.uiresources.components.AbpBottomNavigationBarItem
import com.example.uiresources.icon.Icon
import com.sundravels.androidbestpractices.R
import com.sundravels.androidbestpractices.navigation.TopLevelNavigation


@Composable
fun AbpApp(abpAppState: AbpAppState) {
    Scaffold(
        topBar = {
            AbpTopBar(abpAppState)
        },
        bottomBar = {
            AbpNavigation(abpAppState::navigate,abpAppState.currentDestination)
        }) {

        NavHost(
            navController = abpAppState.navController,
            startDestination = homeScreenRoute,
            modifier = Modifier.padding(it)
        ) {
            composable(homeScreenRoute) {
                HomeScreenRoute()
            }
            composable(favourite_screen_route) {
                FavouriteScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbpTopBar(abpAppState: AbpAppState){
    val currentDestination = abpAppState.destination
    if(currentDestination!=null){
        CenterAlignedTopAppBar(
            modifier = Modifier,
            title = {
                Text(stringResource(id =currentDestination.title?:0), maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.Black,
                    )
                )
            },
            actions = {

                      IconButton(onClick = { /*TODO*/ }) {
                          Icon(
                              painterResource(id = com.example.uiresources.R.drawable.baseline_settings_24),
                              contentDescription = null
                          )
                      }
            },
            navigationIcon = {

//                painterResource(id = com.example.uiresources.R.drawable.baseline_settings_24)
            }

        )
    }
}

@Composable
fun AbpNavigation(navigate: (TopLevelNavigation) -> Unit, currentDestination: NavDestination?) {

    AbpBottomNavigationBar(modifier = Modifier.height(80.dp)) {

        topLevelNavigationList.forEach { bottomBarDestination ->

            AbpBottomNavigationBarItem(icon = {
                when (bottomBarDestination.icon) {
                    is Icon.DrawableResource -> Icon(
                        painterResource(id = bottomBarDestination.icon.id),
                        contentDescription = null
                    )
                    is Icon.ImageVectors -> Icon(
                        painterResource(id = 0),
                        contentDescription = null
                    )
                }
            },
                label = { Text(stringResource(id = bottomBarDestination.text)) },
                selected = currentDestination.isTopLevelDestinationInHierarchy(bottomBarDestination),
                onClick = {
                    navigate(bottomBarDestination)
                })
        }
    }
}
private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelNavigation) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false


