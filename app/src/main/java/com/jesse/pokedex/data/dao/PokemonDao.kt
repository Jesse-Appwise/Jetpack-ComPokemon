package com.jesse.pokedex.data.dao

import androidx.room.Dao
import androidx.room.Query
import be.appwise.room.BaseRoomDao
import com.jesse.pokedex.data.entities.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PokemonDao: BaseRoomDao<Pokemon>("pokemon") {

    @Query("SELECT * FROM pokemon")
    abstract fun findAll(): Flow<List<Pokemon>>

    @Query("SELECT * FROM pokemon WHERE id = :pokemonId LIMIT 1")
    abstract fun findById(pokemonId: Int): Flow<Pokemon?>

    @Query("SELECT * FROM pokemon WHERE id LIKE :query OR name LIKE '%' || :query || '%'")
    abstract fun findAllQueried(query: String): Flow<List<Pokemon>>

}