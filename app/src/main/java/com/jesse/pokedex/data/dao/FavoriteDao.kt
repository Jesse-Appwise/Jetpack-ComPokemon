package com.jesse.pokedex.data.dao

import androidx.room.*
import be.appwise.room.BaseRoomDao
import com.jesse.pokedex.data.entities.favorite.FavoritePokemon
import com.jesse.pokedex.data.entities.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoriteDao : BaseRoomDao<FavoritePokemon>(FavoritePokemon.TABLE_NAME) {

    @Query("SELECT COUNT(*) FROM ${FavoritePokemon.TABLE_NAME} WHERE id = :id")
    abstract suspend fun checkIfPokemonIsFavorite(id: Int): Int

    @Query("SELECT COUNT(*) FROM ${FavoritePokemon.TABLE_NAME} WHERE id = :id")
    abstract fun checkIfPokemonIsFavoriteFlow(id: Int): Flow<Int>

    @Query("SELECT COUNT(*) FROM ${FavoritePokemon.TABLE_NAME}")
    abstract fun countFavoritePokemons(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun setPokemonAsFavorite(pokemon: FavoritePokemon)

    @Delete
    abstract suspend fun removePokemonAsFavorite(pokemon: FavoritePokemon)

    @Transaction
    open suspend fun togglePokemonIsFavorite(pokemonId: Int) {
        if (checkIfPokemonIsFavorite(pokemonId) == 0) {
            setPokemonAsFavorite(FavoritePokemon(pokemonId))
        } else {
            removePokemonAsFavorite(FavoritePokemon(pokemonId))
        }
    }

    @Query("SELECT * FROM pokemon WHERE id IN ( SELECT id FROM ${FavoritePokemon.TABLE_NAME})")
    abstract fun findFavoritePokemons(): Flow<List<Pokemon>>

}