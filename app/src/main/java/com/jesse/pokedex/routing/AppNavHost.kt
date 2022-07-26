package com.jesse.pokedex.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jesse.pokedex.ui.screens.favorites.FavoritesScreen
import com.jesse.pokedex.ui.screens.myteam.MyTeamScreen
import com.jesse.pokedex.ui.screens.pokedex.PokeDexScreen

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
    }
}