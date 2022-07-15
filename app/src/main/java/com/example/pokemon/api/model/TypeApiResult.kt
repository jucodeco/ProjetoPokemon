package com.example.pokemon.api.model

import com.example.pokemon.domain.PokemonType


data class TypeResult(

    val damage_relations : DamageRelations
)


data class DamageRelations(
    val double_damage_from: List<PokemonType>,
    val double_damage_to: List<PokemonType>,
    val half_damage_from: List<PokemonType>,
    val half_damage_to: List<PokemonType>
)
