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
// Cor do tipo
    val typeColorRight1: Int,
    val typeColorRight2: Int?,
    val typeColorLeft1: Int,
    val typeColorLeft2: Int?






    ) : Serializable

data class PokemonDetailsType (
    val name: String,
    val color: Int
)
