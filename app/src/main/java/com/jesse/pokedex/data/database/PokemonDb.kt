package com.jesse.pokedex.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jesse.pokedex.App
import com.jesse.pokedex.data.dao.PokemonDao
import com.jesse.pokedex.data.entities.pokemon.Pokemon
import com.jesse.pokedex.data.entities.pokemon.Sprites
import com.jesse.pokedex.data.entities.pokemon.Type

@Database(
    entities = [
        Pokemon::class
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