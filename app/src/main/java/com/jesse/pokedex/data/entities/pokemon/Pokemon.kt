package com.jesse.pokedex.data.entities.pokemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.room.BaseEntity
import com.jesse.pokedex.R


@Entity
data class Pokemon(
    @PrimaryKey override val id: Int = 0,
    val name: String = "Bulbasaur",
    val sprites: Sprites = Sprites(),
    val types: List<Type> = emptyList()
): BaseEntity{
    fun getNameString() = name.capitalize(Locale.current)
    fun getNumberString(context: Context) = String.format(context.getString(R.string.format_number), id)
    fun hasSecondaryType() = types.size >= 2

    @Composable
    fun PrimaryType(){
        if (types.isNotEmpty()){
            types.first().type.name.TypeChip()
        }
    }

    @Composable
    fun SecondaryType(){
        if (hasSecondaryType()){
            types[1].type.name.TypeChip()
        }
    }

    fun getTypeColor(): Color? = types.firstOrNull()?.type?.name?.color
}
