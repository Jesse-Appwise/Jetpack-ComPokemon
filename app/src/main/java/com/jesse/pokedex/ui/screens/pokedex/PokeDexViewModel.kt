package com.jesse.pokedex.ui.screens.pokedex

import android.util.Log
import be.appwise.core.ui.base.BaseViewModel
import com.jesse.pokedex.repositories.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

class PokeDexViewModel : BaseViewModel() {

    private val query = MutableStateFlow("")
    fun setQuery(newQuery: String) {
        Log.d("PokeDexViewModel", "setQuery: $newQuery")
        query.tryEmit(newQuery)
    }

    private var initialisation = true

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val pokemons = query.debounce {
        if (initialisation) {
            initialisation = false
            0L
        } else {
            500L
        }
    }.flatMapLatest { query ->
        Log.d("PokeDexViewModel", "flatmap: $query")
        if (query.isEmpty()) {
            PokemonRepository.findAllPokemons()
        } else {
            PokemonRepository.findAllQueriedPokemons(query)
        }
    }

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() = launchAndLoad {
        try {
            PokemonRepository.getPokemons()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading(false)
        }
    }

}