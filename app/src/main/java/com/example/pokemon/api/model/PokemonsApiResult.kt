package com.example.pokemon.api.model

import com.example.pokemon.domain.PokemonType
import com.google.gson.annotations.SerializedName

data class PokemonsApiResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonApiResult(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeSlot>,
    val sprites: Sprites
) {

data class Sprites(
    val other: OtherSprites

)
    data class OtherSprites(
        @SerializedName("official-artwork")
        val officialArtwork: OfficialArtwork
    )
    data class OfficialArtwork(
        val front_default: String
    )
}

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)