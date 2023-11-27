package com.example.uiresources.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.uiresources.font.fontFamily

// Set of Material typography styles to start with
val Typography = Typography(

    titleMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily,
        fontSize = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontFamily = fontFamily,
        fontSize = 18.sp
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
    )
)