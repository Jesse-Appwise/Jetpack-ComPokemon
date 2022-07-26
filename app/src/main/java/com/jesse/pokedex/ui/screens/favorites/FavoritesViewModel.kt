package com.jesse.pokedex.ui.screens.favorites

import be.appwise.core.ui.base.BaseViewModel
import com.jesse.pokedex.repositories.PokemonRepository
import kotlinx.coroutines.flow.map

class FavoritesViewModel : BaseViewModel() {

    val pokemons = PokemonRepository.findAllPokemons().map {
        it.take(14)
    }

}