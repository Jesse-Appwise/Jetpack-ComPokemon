package com.jesse.pokedex.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jesse.pokedex.App
import com.jesse.pokedex.data.dao.FavoriteDao
import com.jesse.pokedex.data.dao.PokemonDao
import com.jesse.pokedex.data.dao.TeamDao
import com.jesse.pokedex.data.entities.favorite.FavoritePokemon
import com.jesse.pokedex.data.entities.pokemon.Pokemon
import com.jesse.pokedex.data.entities.pokemon.Sprites
import com.jesse.pokedex.data.entities.pokemon.Type
import com.jesse.pokedex.data.entities.team.TeamMember

@Database(
    entities = [
        Pokemon::class,
        FavoritePokemon::class,
        TeamMember::class
    ], version = 1, exportSchema = false
)
@TypeConverters(
    *[
        Sprites.TypeConverters::class,
        Type.TypeConverters::class
    ]
)
abstract class PokemonDb : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun teamDao(): TeamDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDb? = null

        fun getDatabase(): PokemonDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    App.instance,
                    PokemonDb::class.java,
                    "pokedex.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}