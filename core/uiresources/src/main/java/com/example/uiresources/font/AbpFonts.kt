package com.example.uiresources.font

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.uiresources.R


val fontFamily = FontFamily(
    androidx.compose.ui.text.font.Font(R.font.robotoslab_regular, weight = FontWeight.Normal),
    androidx.compose.ui.text.font.Font(R.font.robotoslab_bold, weight = FontWeight.Bold),
    androidx.compose.ui.text.font.Font(R.font.robotoslab_semibold, weight = FontWeight.SemiBold),
    androidx.compose.ui.text.font.Font(R.font.robotoslab_medium, weight = FontWeight.Medium),
)