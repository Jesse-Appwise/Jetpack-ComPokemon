package com.jesse.pokedex.ui.screens.pokedex

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.jesse.pokedex.data.entities.pokemon.Pokemon
import com.jesse.pokedex.ui.shared.IconChevron
import com.jesse.pokedex.ui.theme.TextStyles

@Composable
@Preview
fun PokemonItemPreview() {
    PokemonItem(
        pokemon = Pokemon()
    )
}

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            elevation = 15.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(pokemon.sprites.frontDefault)
                            .size(50.dp.value.toInt())
                            .build(),
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(50.dp),
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Column {
                        Text(
                            text = pokemon.getNameString(),
                            style = TextStyles.PokemonItemName
                        )
                        Text(
                            text = pokemon.getNumberString(LocalContext.current),
                            style = TextStyles.PokemonItemNr
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    pokemon.PrimaryType()
                    if (pokemon.hasSecondaryType()) {
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                    pokemon.SecondaryType()
                    Spacer(modifier = Modifier.width(6.dp))
                    IconChevron()
                }

            }
        }
    }
}