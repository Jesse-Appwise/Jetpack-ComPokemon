package com.jesse.pokedex.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jesse.pokedex.R
import com.jesse.pokedex.routing.AppBarState
import com.jesse.pokedex.routing.AppNavHost
import com.jesse.pokedex.routing.AppState
import com.jesse.pokedex.ui.theme.PokedexTheme

@Composable
fun PokeDexApp() {
    PokedexTheme {
        AppScaffold()
    }
}


@Composable
fun AppScaffold() {
    val navHostController = rememberNavController()
    val appName = stringResource(id = R.string.app_name)
    val appState = remember {
        mutableStateOf(AppState(AppBarState(title = appName)))
    }
    Scaffold(
        topBar = { TopBar(appState.value, navHostController) }
    ) {
        Box(
            modifier = appState.value.createBackground(Modifier)
        ) {
            AppNavHost(navController = navHostController, appState)
        }
    }
}

@Composable
fun TopBar(appState: AppState, navController: NavHostController) {
    TopAppBar(
        modifier = appState.createBackground(Modifier),
        actions = {
            appState.appBarState.actions?.invoke(this)
        },
        title = {
            Text(
                text = appState.appBarState.title,
                color = appState.appBarState.titleColor
            )
        },
        navigationIcon = if (navController.previousBackStackEntry != null) {
            {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = appState.appBarState.titleColor,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}