package com.jesse.pokedex.repositories

import be.appwise.networking.base.BaseRepository
import com.jesse.pokedex.data.database.PokemonDb
import com.jesse.pokedex.data.networking.RestClient
import kotlinx.coroutines.flow.map

object PokemonRepository : BaseRepository {

    private val db = PokemonDb.getDatabase()
    private val pokeDao = db.pokemonDao()
    private val favoriteDao = db.favoriteDao()
    private val teamDao = db.teamDao()

    fun findAllPokemons() = pokeDao.findAll()
    fun findAllQueriedPokemons(query: String) = pokeDao.findAllQueried(query)
    fun findAllFavoritePokemons() = favoriteDao.findFavoritePokemons()
    fun findTeam() = teamDao.findTeamMemberPokemons()
    fun findPokemonById(pokemonId: Int) = pokeDao.findById(pokemonId)
    fun checkIfPokemonIsFavorite(pokemonId: Int) = favoriteDao.checkIfPokemonIsFavoriteFlow(pokemonId).map { it != 0 }
    suspend fun toggleFavoritePokemon(pokemonId: Int) = favoriteDao.togglePokemonIsFavorite(pokemonId)
    fun countFavoritePokemons() = favoriteDao.countFavoritePokemons()

    suspend fun getPokemons() = doCall(RestClient.pokeService.getPokemons()).also { pokemons ->
        pokeDao.insertMany(pokemons)
    }

}