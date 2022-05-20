package com.example.pokemon.domain

data class Pokemon(
    val number: Int,
    val name: String,
    val types: List<PokemonType>,
    val imageUrl : String
)