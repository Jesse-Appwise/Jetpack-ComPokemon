package com.jesse.pokedex.data.entities.pokemon

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Type(
    val slot: Int,
    val type: TypeName
) {

    class TypeConverters {

        private val listType = object : TypeToken<List<Type>?>() {}.type
        private val objectType = object : TypeToken<Type?>() {}.type

        @TypeConverter
        fun fromList(stats: List<Type>?): String {
            return Gson().toJson(stats, listType)
        }

        @TypeConverter
        fun toList(json: String): List<Type>? {
            return Gson().fromJson(json, listType)
        }

        @TypeConverter
        fun fromObject(stats: Type?): String {
            return Gson().toJson(stats, objectType)
        }

        @TypeConverter
        fun toObject(json: String): Type? {
            return Gson().fromJson(json, objectType)
        }

    }

}

data class TypeName(
    val name: PokemonTypes
)