package com.example.pokemon.lista.usecase



import com.example.pokemon.api.PokemonRepositoryImpl
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.api.model.PokemonsApiResult

class ViewPokemonList(private val pokemonRepositoryImpl: PokemonRepositoryImpl) {

    fun getPokemons(): List<Pokemon>? {

        val pokemonsApiResult: PokemonsApiResult? = pokemonRepositoryImpl.listPokemons()

        return pokemonsApiResult?.results?.map { pokemonResult ->
            val number = pokemonResult.url
                .replace("https://pokeapi.co/api/v2/pokemon/", "")
                .replace("/", "").toInt()

            val pokemonApiResult = pokemonRepositoryImpl.getPokemon(number)
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
