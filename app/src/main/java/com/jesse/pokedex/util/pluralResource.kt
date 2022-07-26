package com.jesse.pokedex.util

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun pluralResource(
    @PluralsRes resId: Int,
    quantity: Int
): String {
    return LocalContext.current.resources
        .getQuantityString(resId, quantity, quantity)
}