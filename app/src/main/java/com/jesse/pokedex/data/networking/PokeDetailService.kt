package com.jesse.pokedex.data.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeDetailService {

    @GET("v2/pokemon/{pokemonId}")
    fun getPokemon(
        @Path("pokemonId") pokemonId: Int
    ): Call<String>

}