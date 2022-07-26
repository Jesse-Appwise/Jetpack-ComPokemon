package com.jesse.pokedex.ui.screens.pokemondetail

import be.appwise.core.ui.base.BaseViewModel
import com.jesse.pokedex.repositories.PokemonRepository
import kotlinx.coroutines.flow.filterNotNull

class PokemonDetailViewModel(val pokemonId: Int): BaseViewModel() {

    val pokemon = PokemonRepository.findPokemonById(pokemonId).filterNotNull()
    val pokemonIsFavorite = PokemonRepository.checkIfPokemonIsFavorite(pokemonId)

    fun togglePokemonIsFavorite() = launchAndLoad {
        PokemonRepository.toggleFavoritePokemon(pokemonId)
    }

}