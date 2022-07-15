package com.example.pokemon.api.types


import com.example.pokemon.api.model.TypeResult

interface TypeRepository {


    fun typePokemon(type: String): TypeResult?
}