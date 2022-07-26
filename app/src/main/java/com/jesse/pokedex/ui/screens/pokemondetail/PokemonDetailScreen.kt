package com.jesse.pokedex.ui.screens.pokemondetail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.jesse.pokedex.R
import com.jesse.pokedex.data.entities.pokemon.Pokemon
import com.jesse.pokedex.routing.AppBarState
import com.jesse.pokedex.routing.AppState
import com.jesse.pokedex.routing.SetAppState
import com.jesse.pokedex.ui.theme.Colors
import com.jesse.pokedex.ui.theme.PokedexTheme

@Preview
@Composable
fun PokemonDetailScreenPreview(){
    PokedexTheme {
        PokemonDetailScreen(pokemonId = 0)
    }
}

@Composable
fun PokemonDetailScreen(
    pokemonId: Int,
    navHostController: NavHostController = rememberNavController(),
    setAppState: SetAppState = {}
){
    val viewModel = PokemonDetailViewModel(pokemonId)
    initAppState(setAppState, viewModel)
    val pokemonState = viewModel.pokemon.collectAsState(initial = null)
    val pokemon = remember { pokemonState }
    pokemon.value ?: return
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.value!!.sprites.frontDefault)
                .size(50.dp.value.toInt())
                .build(),
            contentDescription = pokemon.value!!.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
private fun initAppState(setAppState: SetAppState, viewModel: PokemonDetailViewModel) {
    val pokemonState = viewModel.pokemon.collectAsState(initial = null)
    val pokemon = remember { pokemonState }
    pokemon.value ?: return
    val appBar = AppBarState(
        title = pokemon.value!!.getNameString(),
        titleColor = Colors.Font_White
    )
    setAppState(
        AppState(
            appBarState = appBar,
            createBackground = { modifier ->
                return@AppState if (pokemon.value?.types?.firstOrNull() != null) {
                    modifier.background(
                        pokemon.value?.getTypeColor() ?: return@AppState modifier
                    )
                } else {
                    modifier
                }
            }
        )
    )
}
