package com.jesse.pokedex.data.networking

import com.jesse.pokedex.data.entities.pokemon.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface PokeService {

    @GET("pokemon")
    fun getPokemons(): Call<List<Pokemon>>

}