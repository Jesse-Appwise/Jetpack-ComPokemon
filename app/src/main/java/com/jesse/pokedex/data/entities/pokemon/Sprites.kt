package com.jesse.pokedex.data.entities.pokemon

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class Sprites(
    @SerializedName("front_default") val frontDefault: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
){

    class TypeConverters {

        private val listType = object : TypeToken<List<Sprites>?>() {}.type
        private val objectType = object : TypeToken<Sprites?>() {}.type

        @TypeConverter
        fun fromList(stats: List<Sprites>?): String {
            return Gson().toJson(stats, listType)
        }

        @TypeConverter
        fun toList(json: String): List<Sprites>? {
            return Gson().fromJson(json, listType)
        }

        @TypeConverter
        fun fromObject(stats: Sprites?): String {
            return Gson().toJson(stats, objectType)
        }

        @TypeConverter
        fun toObject(json: String): Sprites? {
            return Gson().fromJson(json, objectType)
        }

    }

}
