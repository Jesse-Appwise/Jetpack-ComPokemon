package com.jesse.pokedex.ui.shared.lists.pokemon

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jesse.pokedex.R
import com.jesse.pokedex.data.entities.pokemon.Pokemon
import com.jesse.pokedex.ui.shared.EmptyView
import com.jesse.pokedex.ui.theme.TextStyles

@Composable
fun PokemonList(
    pokemons: State<List<Pokemon>>,
    emptyView: @Composable () -> Unit = {
        EmptyView(text = stringResource(id = R.string.empty_pokemons))
    },
    onPokemonClicked: (pokemon: Pokemon) -> Unit = {}
) {
    val rememberPokemons = remember { pokemons }
    LazyColumn(
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp)
    ) {
        items(rememberPokemons.value.size) { index ->
            val pokemon = rememberPokemons.value[index]
            PokemonItem(
                pokemon = pokemon,
                onClick = { onPokemonClicked(pokemon) }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
    if (rememberPokemons.value.isEmpty()){
        emptyView()

    }
}