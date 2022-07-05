package com.example.pokemon.lista.usecase


import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.model.PokemonsApiResult
import com.example.pokemon.domain.Pokemon


class ViewPokemonList (private val pokemonRepository: PokemonRepository) {

     fun getPokemons(): List<Pokemon>? {

        val pokemonsApiResult: PokemonsApiResult? = pokemonRepository.listPokemons()

        return pokemonsApiResult?.results?.map { pokemonResult ->
            val number = pokemonResult.url
                .replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "").toInt()

            val pokemonApiResult = pokemonRepository.getPokemon(number)
            val pokemon = Pokemon(
                pokemonApiResult?.id ?: 0,
                pokemonApiResult?.name ?: "",
                pokemonApiResult?.types?.map { type ->
                    type.type
                } ?: emptyList(),
                pokemonApiResult?.sprites?.other?.officialArtwork?.front_default ?: ""
            )
            pokemon
        }
    }
}