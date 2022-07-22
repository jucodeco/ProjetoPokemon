package com.example.pokemon.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.model.DamageRelations
import com.example.pokemon.api.model.PokemonApiResult
import com.example.pokemon.api.model.PokemonTypeSlot
import com.example.pokemon.api.model.TypeResult
import com.example.pokemon.api.types.TypeRepository
import com.example.pokemon.domain.PokemonType
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DetailsPokemonViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val observer = mock<Observer<PokemonDetails>>()
    private val pokemonRepository = mock<PokemonRepository>()
    private val typeRepository = mock<TypeRepository>()
    private val detailsPokemonViewModel = DetailsPokemonViewModel(pokemonRepository, typeRepository)


    @Before
    fun setup() {
        detailsPokemonViewModel.pokemonDetails.observeForever(observer)

    }

    @Test
    fun `test loadPokemonDetails using PokemonRepository and fire pokemon type , show fire pokemon details`() {
        whenever(pokemonRepository.getPokemon(4000)).doReturn(
            PokemonApiResult(
                4000,
                "ju",
                listOf(PokemonTypeSlot(1, PokemonType("fire"))),
                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("url"))),
                listOf(PokemonApiResult.PokemonStats(2, 1, PokemonApiResult.PokemonStatName("nome"))),
                40, 87
            )

        )
        whenever(typeRepository.typePokemon("fire")).doReturn(
            TypeResult(
                DamageRelations(
                    listOf(PokemonType("water")),
                    listOf(PokemonType("grass")),
                    listOf(PokemonType("poison")),
                    listOf(PokemonType("rock"))
                )
            )
        )
        detailsPokemonViewModel.loadPokemonDetails(4000)
        verify(observer).onChanged(
            PokemonDetails(
                "Ju",
                4000,
                "url",
                listOf(Pair("nome", 2)),
                40,
                87,
                R.color.colorfire,
                listOf(PokemonDetailsType("water", R.color.colorwater)),
                listOf(PokemonDetailsType("poison", R.color.colorpoison))
            )
        )

    }

    @Test
    fun `test loadPokemonDetails using PokemonRepository and poison pokemon type , show poison pokemon details`() {
        whenever(pokemonRepository.getPokemon(4000)).doReturn(
            PokemonApiResult(
                4000,
                "ju",
                listOf(PokemonTypeSlot(1, PokemonType("poison")), PokemonTypeSlot(2, PokemonType("water"))),

                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("url"))),
                listOf(PokemonApiResult.PokemonStats(2, 1, PokemonApiResult.PokemonStatName("nome"))),
                40, 87
            )
        )
        whenever(typeRepository.typePokemon("poison")).doReturn(
            TypeResult(
                DamageRelations(
                    listOf(PokemonType("bug")),
                    listOf(PokemonType("grass")),
                    listOf(PokemonType("poison")),
                    listOf(PokemonType("rock"))
                )
            )
        )
        whenever(typeRepository.typePokemon("water")).doReturn(
            TypeResult(
                DamageRelations(
                    listOf(PokemonType("electric")),
                    listOf(PokemonType("grass")),
                    listOf(PokemonType("poison")),
                    listOf(PokemonType("rock"))
                )
            )
        )



        detailsPokemonViewModel.loadPokemonDetails(4000)
        verify(observer).onChanged(
            PokemonDetails(
                "Ju",
                4000,
                "url",
                listOf(Pair("nome", 2)),
                40,
                87,
                R.color.colorpoison,
                listOf(
                    PokemonDetailsType("bug", R.color.colorbug),
                    PokemonDetailsType("electric", R.color.colorelectric)
                ),
                listOf(
                    PokemonDetailsType("poison", R.color.colorpoison)




                )
            )
        )


    }


}