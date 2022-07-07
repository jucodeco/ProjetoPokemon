package com.example.pokemon.details

data class PokemonDetails  (
    val name : String,
    val number: Int,
    val image: String,
    val atributo: List<Pair<String,Int>>,
    val height: Int,
    val weight: Int,
    val backgroudColor: Int

        )



