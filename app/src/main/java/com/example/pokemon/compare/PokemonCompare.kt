package com.example.pokemon.compare

import java.io.Serializable

data class PokemonCompare(
    //Imagem
    val imageLeft: String,
    val imageRight: String,
   //Nome
    val nameLeft: String,
    val nameRight: String,
//Tipos
    val typeRight1: String,
    val typeRight2: String?,
    val typeLeft1: String,
    val typeLeft2: String?,







    ) : Serializable

data class PokemonDetailsType (
    val name: String,
    val color: Int
)
