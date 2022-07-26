package com.jesse.pokedex.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object TextStyles {
    val SearchHint = TextStyle.Default.copy(
        color = Colors.Font_Light_Secondary,
        fontWeight = FontWeight(400),
        fontSize = 17.sp
    )

    val FeatureButtonLabel = TextStyle.Default.copy(
        color = Colors.Font_White,
        fontWeight = FontWeight(700),
        fontSize = 18.sp,
        lineHeight = 22.sp
    )

    val FeatureButtonSubscript = TextStyle.Default.copy(
        color = Colors.Font_White_50,
        fontWeight = FontWeight(400),
        fontSize = 15.sp,
        lineHeight = 22.sp
    )

    val PokemonItemName = TextStyle.Default.copy(
        color = Colors.Font_Black,
        fontWeight = FontWeight(700),
        fontSize = 17.sp,
        lineHeight = 20.29.sp
    )

    val PokemonItemNr = TextStyle.Default.copy(
        color = Colors.Font_Grey,
        fontWeight = FontWeight(400),
        fontSize = 15.sp,
        lineHeight = 17.9.sp
    )

    val Type = TextStyle.Default.copy(
        color = Colors.Font_White,
        fontWeight = FontWeight(400),
        fontSize = 12.sp,
        lineHeight = 14.32.sp
    )

    val EmptyText = TextStyle.Default.copy(
        color = Colors.Font_White,
        fontWeight = FontWeight(700),
        fontSize = 18.sp,
        lineHeight = 22.sp
    )
}