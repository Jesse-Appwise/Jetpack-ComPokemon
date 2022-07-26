package com.jesse.pokedex.routing

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jesse.pokedex.ui.theme.Colors

data class AppBarState(
    val title: String = "",
    val titleColor: Color = Colors.Font_Black,
    val actions: (@Composable RowScope.() -> Unit)? = null
)

data class AppState(
    val appBarState: AppBarState,
    val createBackground: (modifier: Modifier) -> Modifier = { it }
)


typealias SetAppState = (AppState) -> Unit

