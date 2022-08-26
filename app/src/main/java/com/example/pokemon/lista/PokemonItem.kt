package com.example.pokemon.lista

data class PokemonItem(
    val image: String,
    val name: String,
    val number: String,
    val type1: String,
    val type1color: Int,
    val type2: String?,
    val type2color: Int?,
    val id: Int,
    val isfav: Boolean,

)
