package com.example.pokemon.compare

import com.example.pokemon.details.PokemonDetailsType
import com.example.pokemon.domain.Pokemon
import java.io.Serializable

data class PokemonCompare(
    //Imagem
    val imageLeft: String,
    val imageRight: String,
   //Nome
    val nameLeft: String?,
    val nameRight: String?,
//Tipos
    val typeRight1: String,
    val typeRight2: String?,
    val typeLeft1: String,
    val typeLeft2: String?,
// Cor do tipo
    val typeColorRight1: Int,
    val typeColorRight2: Int?,
    val typeColorLeft1: Int,
    val typeColorLeft2: Int?,
    // Status
    val statsLeft: List<Pair<String,Int>>,
    val statsRight: List<Pair<String,Int>>,

    val resistanceLeft: List<PokemonCompareType>,
    val resistanceRight: List<PokemonCompareType>,


    val weaknessLeft: List<PokemonCompareType>?,
    val weaknessRight: List<PokemonCompareType>?,

   val hpIconLeft: Int,
   val hpIconRight: Int,

    val attackIconLeft: Int,
    val attackIconRight: Int,

    val defenseIconLeft:Int,
    val defenseIconRight: Int,

    val spArkIconLeft:Int,
    val spArkIconRight:Int,

    val spDefIconLeft: Int,
    val spDefIconRight: Int,

    val speedIconLeft: Int,
    val speedIconRight: Int,




    ) : Serializable

data class PokemonCompareType (
    val name: String,
    val color: Int,

)
