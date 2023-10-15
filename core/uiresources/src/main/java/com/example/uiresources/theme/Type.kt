package com.example.uiresources.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.uiresources.font.fontFamily

// Set of Material typography styles to start with
val Typography = Typography(

    titleMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily,
        fontSize = 22.sp

    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = fontFamily,
        fontSize = 18.sp
    )
)