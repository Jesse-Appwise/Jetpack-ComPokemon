package com.jesse.pokedex.ui.screens.favorites

import be.appwise.core.ui.base.BaseViewModel
import com.jesse.pokedex.repositories.PokemonRepository

class FavoritesViewModel : BaseViewModel() {

    val pokemons = PokemonRepository.findAllFavoritePokemons()

}