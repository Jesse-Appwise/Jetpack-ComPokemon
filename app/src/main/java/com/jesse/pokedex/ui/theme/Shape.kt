package com.jesse.pokedex.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

object Shapes{
    val small = RoundedCornerShape(10.dp)
    val medium = RoundedCornerShape(10.dp)
    val large = RoundedCornerShape(4.dp)

    val themeShapes = Shapes(
        small, medium, large
    )
}