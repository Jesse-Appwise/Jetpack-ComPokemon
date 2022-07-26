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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun setPokemonAsFavorite(pokemon: FavoritePokemon)

    @Delete
    abstract suspend fun removePokemonAsFavorite(pokemon: FavoritePokemon)

    @Transaction
    open suspend fun togglePokemonIsFavorite(pokemon: Pokemon) {
        if (checkIfPokemonIsFavorite(pokemon.id) != 0) {
            setPokemonAsFavorite(FavoritePokemon(pokemon.id))
        } else {
            removePokemonAsFavorite(FavoritePokemon(pokemon.id))
        }
    }

    @Query("SELECT * FROM pokemon WHERE id IN ( SELECT id FROM ${FavoritePokemon.TABLE_NAME})")
    abstract fun findFavoritePokemons(): Flow<List<Pokemon>>

}