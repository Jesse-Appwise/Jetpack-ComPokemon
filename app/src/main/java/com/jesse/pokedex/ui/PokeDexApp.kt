package com.jesse.pokedex.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jesse.pokedex.R
import com.jesse.pokedex.ui.screens.pokedex.PokeDexScreen
import com.jesse.pokedex.ui.theme.PokedexTheme

@Composable
fun PokeDexApp() {
    PokedexTheme {
        AppScaffold()
    }
}


@Composable
fun AppScaffold() {
    Scaffold(
        topBar = { TopBar() }
    ) {
        ScreenContainer()
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
fun ScreenContainer() {
    PokeDexScreen()
}