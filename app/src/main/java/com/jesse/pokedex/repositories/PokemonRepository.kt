package com.jesse.pokedex.repositories

import be.appwise.networking.base.BaseRepository
import com.jesse.pokedex.data.database.PokemonDb
import com.jesse.pokedex.data.networking.RestClient

object PokemonRepository : BaseRepository {

    private val pokeDao = PokemonDb.getDatabase().pokemonDao()

    fun findAllPokemons() = pokeDao.findAll()
    fun findAllQueriedPokemons(query: String) = pokeDao.findAllQueried(query)

    suspend fun getPokemons() = doCall(RestClient.pokeService.getPokemons()).also { pokemons ->
        pokeDao.insertMany(pokemons)
    }

}