package com.example.pokemon.compare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.model.PokemonApiResult
import com.example.pokemon.api.model.PokemonTypeSlot
import com.example.pokemon.domain.PokemonType
import com.example.pokemon.lista.usecase.ViewPokemonList
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule



class ComparePokemonViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val observer = mock<Observer<PokemonCompare>>()
    private val pokemonRepository = mock<PokemonRepository>()
    private val comparePokemonViewModel = ComparePokemonViewModel(pokemonRepository)
    private val viewPokemonList = mock<ViewPokemonList>()


    @Before
    fun setup() {
        comparePokemonViewModel.pokemonCompare.observeForever(observer)

    }

    @Test
    fun `given my pokemon repository show on the screen the image of the two pokemons`() {
        whenever(pokemonRepository.getPokemon(1)).doReturn(
            PokemonApiResult(
                1,
                "bulbasaur",
                listOf(PokemonTypeSlot(1, PokemonType("grass"))),
                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("url1"))),
                listOf(PokemonApiResult.PokemonStats(45, 49, PokemonApiResult.PokemonStatName("bulbasaur"))),
                7, 69

            )

        )

        whenever(pokemonRepository.getPokemon(2)).doReturn(
            PokemonApiResult(
                2,
                "ivysaur",
                listOf(PokemonTypeSlot(2, PokemonType("poison"))),

                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("urlright"))),
                listOf(PokemonApiResult.PokemonStats(60, 62, PokemonApiResult.PokemonStatName("ivysaur"))),
                10, 130
            )

        )
        comparePokemonViewModel.comparePokemon(1, 2)
        verify(observer).onChanged(
            PokemonCompare(
                "url1",
                "urlright",
                "bulbasaur",
                "ivysaur",
                "",
                null,
                "",
                null,


            )


        )
    }

    @Test
    fun `given my pokemon repository show on the screen the image of the two pokemons 2`() {
        whenever(pokemonRepository.getPokemon(3)).doReturn(
            PokemonApiResult(
                3,
                "bulbasaur",
                listOf(PokemonTypeSlot(3, PokemonType("grass"))),
                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("url1"))),
                listOf(PokemonApiResult.PokemonStats(45, 49, PokemonApiResult.PokemonStatName("bulbasaur"))),
                7, 69
            )

        )

        whenever(pokemonRepository.getPokemon(4)).doReturn(
            PokemonApiResult(
                4,
                "ivysaur",
                listOf(PokemonTypeSlot(4, PokemonType("poison"))),
                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("url2"))),
                listOf(PokemonApiResult.PokemonStats(60, 62, PokemonApiResult.PokemonStatName("ivysaur"))),
                10, 130
            )

        )
        comparePokemonViewModel.comparePokemon(3, 4)
        verify(observer).onChanged(
            PokemonCompare(
                "url1",
                "url2",
                "bulbasaur",
                "ivysaur",
                "",
                null,
                "",
                null,


            )


        )
    }

    @Test
    fun `given my pokemon repository show on screen the name of the two pokemons`() {
        whenever(pokemonRepository.getPokemon(5)).doReturn(
            PokemonApiResult(
                5,
                "venusaur",
                listOf(PokemonTypeSlot(5, PokemonType("grass"))),
                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("url1"))),
                listOf(PokemonApiResult.PokemonStats(45, 49, PokemonApiResult.PokemonStatName("bulbasaur"))),
                7, 69
            )


        )
        whenever(pokemonRepository.getPokemon(6)).doReturn(
            PokemonApiResult(
                6,
                "charmander",
                listOf(PokemonTypeSlot(6, PokemonType("grass"))),
                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("url2"))),
                listOf(PokemonApiResult.PokemonStats(45, 49, PokemonApiResult.PokemonStatName("bulbasaur"))),
                7, 69
            )


        )

        comparePokemonViewModel.comparePokemon(5, 6)
        verify(observer).onChanged(
            PokemonCompare(
                "url1",
                "url2",
                "venusaur",
                "charmander",
                "",
                null,
                "",
                null,
               


            )


        )
    }
}



