package com.example.pokemon.details

import java.io.Serializable

data class PokemonDetails(
    val name: String,
    val number: Int,
    val image: String,
    val stats: List<Pair<String, Int>>,
    val height: Int?,
    val weight: Int,
    val backgroudColor: Int

) : Serializable



