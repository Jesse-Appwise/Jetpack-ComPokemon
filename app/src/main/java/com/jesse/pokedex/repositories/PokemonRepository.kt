package com.jesse.pokedex.repositories

import be.appwise.networking.base.BaseRepository
import com.jesse.pokedex.data.database.PokemonDb
import com.jesse.pokedex.data.networking.RestClient

object PokemonRepository : BaseRepository {

    private val db = PokemonDb.getDatabase()
    private val pokeDao = db.pokemonDao()
    private val favoriteDao = db.favoriteDao()
    private val teamDao = db.teamDao()

    fun findAllPokemons() = pokeDao.findAll()
    fun findAllQueriedPokemons(query: String) = pokeDao.findAllQueried(query)
    fun findAllFavoritePokemons() = favoriteDao.findFavoritePokemons()
    fun findTeam() = teamDao.findTeamMemberPokemons()

    suspend fun getPokemons() = doCall(RestClient.pokeService.getPokemons()).also { pokemons ->
        pokeDao.insertMany(pokemons)
    }

}