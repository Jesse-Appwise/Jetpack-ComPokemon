package com.jesse.pokedex.ui.screens.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import be.appwise.core.ui.base.BaseViewModel
import com.jesse.pokedex.R
import com.jesse.pokedex.routing.AppBarState
import com.jesse.pokedex.routing.AppState
import com.jesse.pokedex.routing.Destinations
import com.jesse.pokedex.routing.SetAppState
import com.jesse.pokedex.ui.shared.EmptyView
import com.jesse.pokedex.ui.shared.lists.pokemon.PokemonList
import com.jesse.pokedex.ui.theme.Colors
import com.jesse.pokedex.ui.theme.PokedexTheme
import com.jesse.pokedex.ui.theme.Shapes
import com.jesse.pokedex.ui.theme.TextStyles
import com.jesse.pokedex.util.pluralResource

@Preview
@Composable
private fun PokeDexScreenPreview() {
    PokedexTheme {
        Surface(
            color = Color.White
        ) {
            PokeDexScreen()
        }
    }
}

@Composable
fun PokeDexScreen(
    navHostController: NavHostController = rememberNavController(),
    setAppState: SetAppState = {}
) {
    val viewModel = PokeDexViewModel()
    initAppState(setAppState)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 14.dp)
    ) {
        SearchView(onQueryChanged = viewModel::setQuery)
        Spacer(modifier = Modifier.height(19.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MyTeamButton(
                modifier = Modifier.weight(1f),
                onClick = { navHostController.navigate(Destinations.MyTeamScreen.route) }
            )
            Spacer(modifier = Modifier.width(9.dp))
            FavoritesButton(
                viewModel.countFavoritePokemons.collectAsState(initial = 0),
                modifier = Modifier.weight(1f),
                onClick = { navHostController.navigate(Destinations.FavoritesScreen.route) }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        PokemonList(
            pokemons = viewModel.pokemons.collectAsState(emptyList()),
            emptyView = {
                EmptyView(
                    text = stringResource(id = R.string.empty_pokemons),
                    textColor = Colors.Font_Black
                )
            },
            onPokemonClicked = {
                navHostController.navigate("pokemon/${it.id}")
            }
        )
    }
    LoadingIndicator(viewModel = viewModel)
}

@Composable
private fun initAppState(setAppState: SetAppState) {
    val appBar = AppBarState(
        title = stringResource(id = R.string.app_name)
    )
    setAppState(AppState(appBar))
}

@Composable
fun SearchView(modifier: Modifier = Modifier, onQueryChanged: (query: String) -> Unit) {
    val query = remember {
        mutableStateOf("")
    }
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = query.value,
        onValueChange = {
            query.value = it
            onQueryChanged(it)
        },
        maxLines = 1,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.Background_Grey, Shapes.medium)
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(end = 6.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search",
                    tint = Colors.Font_Light_Secondary
                )
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (query.value.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.search_pokemon),
                            style = TextStyles.SearchHint
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun MyTeamButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    FeatureButton(
        title = stringResource(R.string.my_team),
        subscript = "4 pokemons",
        gradientColors = listOf(
            Colors.My_Team_Gradient_Start,
            Colors.My_Team_Gradient_End
        ),
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
fun FavoritesButton(
    pokemonCount: State<Int>,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val count = remember { pokemonCount }
    FeatureButton(
        title = stringResource(R.string.my_favorites),
        subscript = pluralResource(resId = R.plurals.x_pokemons, quantity = count.value),
        gradientColors = listOf(
            Colors.Favorites_Gradient_Start,
            Colors.Favorites_Gradient_End
        ),
        modifier = modifier,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeatureButton(
    title: String,
    subscript: String,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = Shapes.medium,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.linearGradient(
                    colors = gradientColors,
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "Pokeball"
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(36.dp))
                Text(
                    text = title,
                    style = TextStyles.FeatureButtonLabel
                )
                Text(
                    text = subscript,
                    style = TextStyles.FeatureButtonSubscript
                )
            }
        }
    }
}

@Composable
fun LoadingIndicator(viewModel: BaseViewModel) {
    val isLoading by viewModel.loading.observeAsState(false)
    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}