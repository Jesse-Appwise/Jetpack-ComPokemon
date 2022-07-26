package com.jesse.pokedex.routing

enum class Destinations(
    val route: String
) {
    PokedexScreen("pokedex"),
    FavoritesScreen("favorites"),
    MyTeamScreen("my_team");
}