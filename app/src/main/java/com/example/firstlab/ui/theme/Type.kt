package com.example.firstlab.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// 💡 Justificación: Buscamos una fuente Sans-serif (GameTitleFont) para los títulos
// por su estilo moderno y tecnológico, adecuado para una wiki de juegos.
val GameTitleFont = FontFamily.SansSerif

// 💡 Justificación: Usamos Roboto/Default (BodyTextFont) para el cuerpo del texto
// para asegurar una excelente legibilidad en descripciones y listas.
val BodyTextFont = FontFamily.Default

// Definición de la Tipografía
val AppTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = GameTitleFont,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = BodyTextFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = BodyTextFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

val Typography = AppTypography