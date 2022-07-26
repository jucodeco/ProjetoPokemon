package com.example.pokemon.api

import com.example.pokemon.api.model.PokemonApiResult
import com.example.pokemon.api.model.PokemonsApiResult



interface PokemonRepository {

    fun listPokemons (limit: Int = 151): PokemonsApiResult?
    fun getPokemon(number: Int): PokemonApiResult

}