package com.jesse.pokedex.ui.screens.myteam

import be.appwise.core.ui.base.BaseViewModel
import com.jesse.pokedex.repositories.PokemonRepository
import kotlinx.coroutines.flow.map

class MyTeamViewModel : BaseViewModel() {

    val pokemons = PokemonRepository.findAllPokemons().map { it.take(6) }

}