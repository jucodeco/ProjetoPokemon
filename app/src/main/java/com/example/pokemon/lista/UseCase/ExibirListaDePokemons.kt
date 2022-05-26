package com.example.pokemon.lista.UseCase

import android.util.Log
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.api.model.PokemonsApiResult

class ExibirListaDePokemons {

    fun getPokemons(): List<Pokemon>? {

        val pokemonsApiResult: PokemonsApiResult? = PokemonRepository.listPokemons()

        Log.d("Pokemon", pokemonsApiResult.toString())

        return pokemonsApiResult?.results?.map { pokemonResult ->
            val number = pokemonResult.url
                .replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "").toInt()

            val pokemonApiResult = PokemonRepository.getPokemon(number)
            Pokemon(
                pokemonApiResult?.id ?: 0,
                pokemonApiResult?.name ?: "",
                pokemonApiResult?.types?.map { type ->
                    type.type
                } ?: emptyList(),
                pokemonApiResult?.sprites?.other?.officialArtwork?.front_default?:""
            )
        }
    }
}
