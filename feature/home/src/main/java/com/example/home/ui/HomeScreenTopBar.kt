package com.example.home.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.uiresources.theme.Purple500
import com.sundravels.androidbestpractices.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(clicked:()->Unit) {
    val context = LocalContext.current
    Column {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "What do you want to\ncook today ?",
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )


        }
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = "",
            onValueChange = {

            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = Purple500)
            },
            readOnly = true,
            placeholder = {
                Text(
                    text = "Search 'Recipes'",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 16.sp
                    )
                )
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 10.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    ambientColor = MaterialTheme.colorScheme.onBackground
                )
                .clickable {
                    clicked()
                },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                disabledIndicatorColor = MaterialTheme.colorScheme.onSecondary
            ),
            enabled = false
        )
    }

}