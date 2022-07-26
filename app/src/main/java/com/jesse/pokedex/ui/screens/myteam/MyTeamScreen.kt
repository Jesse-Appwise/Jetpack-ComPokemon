package com.jesse.pokedex.ui.screens.myteam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jesse.pokedex.R
import com.jesse.pokedex.routing.AppBarState
import com.jesse.pokedex.routing.AppState
import com.jesse.pokedex.routing.SetAppState
import com.jesse.pokedex.ui.shared.EmptyView
import com.jesse.pokedex.ui.shared.lists.pokemon.PokemonList
import com.jesse.pokedex.ui.theme.Colors
import com.jesse.pokedex.ui.theme.PokedexTheme


@Preview
@Composable
fun MyTeamScreenPreview() {
    PokedexTheme {
        MyTeamScreen()
    }
}

@Composable
fun MyTeamScreen(
    navHostController: NavHostController = rememberNavController(),
    setAppState: SetAppState = {}
) {
    val viewModel = MyTeamViewModel()
    initAppState(setAppState)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PokemonList(
            pokemons = viewModel.pokemons.collectAsState(initial = emptyList()),
            emptyView = {
                EmptyView(
                    text = stringResource(R.string.empty_team)
                )
            }
        )
    }
}

@Composable
fun initAppState(setAppState: SetAppState) {
    val appBar = AppBarState(
        title = stringResource(id = R.string.my_team),
        titleColor = Colors.Font_White
    )
    setAppState(
        AppState(
            appBarState = appBar,
            createBackground = { modifier: Modifier ->
                modifier.background(
                    Brush.horizontalGradient(
                        listOf(Colors.My_Team_Gradient_Start, Colors.My_Team_Gradient_End)
                    )
                )
            }
        )
    )
}
