package com.jesse.pokedex.data.networking

import be.appwise.networking.base.BaseRestClient

object RestClient : BaseRestClient() {

    override val protectedClient = false
    override fun getBaseUrl() = ""

    val pokeService: PokeService by lazy {
        createRetrofit(baseUrl = "https://stoplight.io/mocks/appwise-be/pokemon/57519009/").create(PokeService::class.java)
    }

    val pokeDetailService: PokeDetailService by lazy {
        createRetrofit(baseUrl = " https://pokeapi.co/api/").create(PokeDetailService::class.java)
    }
}