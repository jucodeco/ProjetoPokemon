package com.example.pokemon.lista.usecase

import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.model.PokemonApiResult
import com.example.pokemon.api.model.PokemonResult
import com.example.pokemon.api.model.PokemonTypeSlot
import com.example.pokemon.api.model.PokemonsApiResult
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.PokemonType
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Test


class ViewPokemonListTest {

    private val pokemonRepository = mock<PokemonRepository>()
    private val viewPokemonList = ViewPokemonList(pokemonRepository)


    @Test
    fun `given pokemon list when pokemonApiResult is called then check if ViewPokemonList is loaded`() {
        whenever(pokemonRepository.listPokemons(151)).doReturn(
            PokemonsApiResult(
                1,
                null,
                null,
                listOf(PokemonResult("nome", "https://pokeapi.co/api/v2/pokemon/1/"))
            )
        )
        whenever(pokemonRepository.getPokemon(1)).doReturn(
            PokemonApiResult(
                1, "nome", listOf(PokemonTypeSlot(1, PokemonType("tipo"))),
                PokemonApiResult.Sprites(other = PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork(front_default = "http")))
            , emptyList(),0,0)
        )
        Assert.assertEquals(
            listOf(Pokemon(1, "nome", listOf(PokemonType("tipo")), "http")),

            viewPokemonList.getPokemons()

        )


    }
}
