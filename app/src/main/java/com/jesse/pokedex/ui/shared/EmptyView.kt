package com.jesse.pokedex.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jesse.pokedex.ui.theme.Colors
import com.jesse.pokedex.ui.theme.TextStyles


@Composable
fun EmptyView(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxSize(),
    textColor: Color = Colors.Font_White
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyles.EmptyText.copy(color = textColor),
            textAlign = TextAlign.Center
        )
    }
}