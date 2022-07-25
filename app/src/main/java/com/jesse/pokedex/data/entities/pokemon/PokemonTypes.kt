package com.jesse.pokedex.data.entities.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.annotations.SerializedName
import com.jesse.pokedex.ui.theme.TextStyles

@Composable
@Preview
fun TypeChipPreview() {
    Column {
        PokemonTypes.values().forEach {
            it.TypeChip(
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

enum class PokemonTypes(val color: Color) {
    @SerializedName("normal")
    NORMAL(Color(0xFFA8A77A)),
    @SerializedName("fire")
    FIRE(Color(0xFFEE8130)),
    @SerializedName("water")
    WATER(Color(0xFF6390F0)),
    @SerializedName("electric")
    ELECTRIC(Color(0xFFF7D02C)),
    @SerializedName("grass")
    GRASS(Color(0xFF7AC74C)),
    @SerializedName("ice")
    ICE(Color(0xFF96D9D6)),
    @SerializedName("fighting")
    FIGHTING(Color(0xFFC22E28)),
    @SerializedName("poison")
    POISON(Color(0xFFA33EA1)),
    @SerializedName("ground")
    GROUND(Color(0xFFE2BF65)),
    @SerializedName("flying")
    FLYING(Color(0xFFA98FF3)),
    @SerializedName("psychic")
    PSYCHIC(Color(0xFFF95587)),
    @SerializedName("bug")
    BUG(Color(0xFFA6B91A)),
    @SerializedName("rock")
    ROCK(Color(0xFFB6A136)),
    @SerializedName("ghost")
    GHOST(Color(0xFF735797)),
    @SerializedName("dragon")
    DRAGON(Color(0xFF6F35FC)),
    @SerializedName("dark")
    DARK(Color(0xFF705746)),
    @SerializedName("steel")
    STEEL(Color(0xFFB7B7CE)),
    @SerializedName("fairy")
    FAIRY(Color(0xFFD685AD))
    ;

    @Composable
    fun TypeChip(modifier: Modifier = Modifier) {
        Card(
            modifier = modifier,
            backgroundColor = this.color,
            shape = RoundedCornerShape(Int.MAX_VALUE.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                text = this.name.lowercase().capitalize(Locale.current),
                style = TextStyles.Type
            )
        }
    }
}