package com.jesse.pokedex.ui.screens.myteam

import be.appwise.core.ui.base.BaseViewModel
import com.jesse.pokedex.repositories.PokemonRepository

class MyTeamViewModel : BaseViewModel() {

    val pokemons = PokemonRepository.findTeam()

}