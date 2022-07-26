package com.jesse.pokedex.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jesse.pokedex.ui.screens.favorites.FavoritesScreen
import com.jesse.pokedex.ui.screens.myteam.MyTeamScreen
import com.jesse.pokedex.ui.screens.pokedex.PokeDexScreen
import com.jesse.pokedex.ui.screens.pokemondetail.PokemonDetailScreen

@Composable
fun AppNavHost(navController: NavHostController, appState: MutableState<AppState>) {
    val updateAppState = { newState: AppState ->  appState.value = newState }
    NavHost(navController = navController, startDestination = Destinations.PokedexScreen.route) {
        //PokeDex
        composable(Destinations.PokedexScreen.route) {
            PokeDexScreen(
                navHostController = navController,
                setAppState = updateAppState
            )
        }

        //My Team
        composable(Destinations.MyTeamScreen.route){
            MyTeamScreen(
                navHostController = navController,
                setAppState = updateAppState
            )
        }

        //Favorites
        composable(Destinations.FavoritesScreen.route){
            FavoritesScreen(
                navHostController = navController,
                setAppState = updateAppState
            )
        }

        composable(
            Destinations.PokemonDetailScreen.route,
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ){
            PokemonDetailScreen(
                pokemonId = it.arguments?.getInt("pokemonId") ?: 0,
                navHostController = navController,
                setAppState = updateAppState
            )
        }
    }
}