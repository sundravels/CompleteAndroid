package com.example.favourite.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uiresources.theme.PurpleGray60
import com.example.uiresources.theme.PurpleGray90


@Composable
fun FavouriteTopBar(){
    Column(modifier = Modifier.padding(top = 60.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)) {
        Text(text = "All Saved Recipes", style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground))
        Text(text = "Your Favourite Sugary Recipes", style = MaterialTheme.typography.titleSmall.copy(
            PurpleGray60))
        Divider(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), thickness = 0.5.dp, color = MaterialTheme.colorScheme.outline)

    }
}