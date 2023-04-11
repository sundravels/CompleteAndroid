package com.example.uiresources.font

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.uiresources.R

@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@OptIn(ExperimentalTextApi::class)
val fontName = GoogleFont("Poppins")

@OptIn(ExperimentalTextApi::class)
val fontBold = GoogleFont("Poppins-Bold")

@OptIn(ExperimentalTextApi::class)
val fontSemiBold = GoogleFont("Poppins-SemiBold")

@OptIn(ExperimentalTextApi::class)
val fontMedium = GoogleFont("Poppins-Medium")

@OptIn(ExperimentalTextApi::class)
val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = fontBold, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = fontSemiBold, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = fontMedium, fontProvider = provider, weight = FontWeight.Medium),

    )