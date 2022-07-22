package com.example.pokemon.compare

import java.io.Serializable

data class PokemonCompare(
    //Imagem
    val imageLeft: String,
    val imageRight: String,
   //Nome
    val nameLeft: String,
    val nameRight: String,





) : Serializable

data class PokemonDetailsType (
    val name: String,
    val color: Int
)
