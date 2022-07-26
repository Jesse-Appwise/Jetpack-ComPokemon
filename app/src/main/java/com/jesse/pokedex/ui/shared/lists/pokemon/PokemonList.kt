package com.jesse.pokedex.ui.shared.lists.pokemon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jesse.pokedex.data.entities.pokemon.Pokemon

@Composable
fun PokemonList(pokemons: State<List<Pokemon>>) {
    val rememberPokemons = remember { pokemons }
    LazyColumn(
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp)
    ) {
        items(rememberPokemons.value.size) { index ->
            PokemonItem(pokemon = rememberPokemons.value[index])
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}