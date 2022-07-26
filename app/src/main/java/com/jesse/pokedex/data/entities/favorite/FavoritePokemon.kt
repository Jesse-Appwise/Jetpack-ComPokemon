package com.jesse.pokedex.data.entities.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.room.BaseEntity

@Entity(
    tableName = FavoritePokemon.TABLE_NAME
)
data class FavoritePokemon(
    @PrimaryKey override val id: Int
): BaseEntity{

    companion object{
        const val TABLE_NAME = "favorite"
    }

}
