package com.jesse.pokedex.ui.shared

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jesse.pokedex.R
import com.jesse.pokedex.ui.theme.Colors

@Composable
@Preview
fun IconChevron(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_chevron),
        contentDescription = "next",
        tint = Colors.Font_Grey
    )
}