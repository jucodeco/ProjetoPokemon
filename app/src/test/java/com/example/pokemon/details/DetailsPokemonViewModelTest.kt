package com.example.pokemon.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.model.PokemonApiResult
import com.example.pokemon.api.model.PokemonTypeSlot
import com.example.pokemon.domain.PokemonType
import com.example.pokemon.lista.PokemonAdapter
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.verification.VerificationMode

class DetailsPokemonViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val observer = mock<Observer<PokemonDetails>>()
    private val pokemonRepository = mock<PokemonRepository>()
    private val detailsPokemonViewModel = DetailsPokemonViewModel (pokemonRepository)
    private val pokemonAdapter = mock<PokemonAdapter>()


    @Before
    fun setup() {
        detailsPokemonViewModel.pokemonDetails.observeForever(observer)

    }

    @Test
    fun  `test loadPokemonDetails using PokemonRepository and fire pokemon type , show fire pokemon details` (){
        whenever(pokemonRepository.getPokemon(4000)).doReturn(
            PokemonApiResult(
                4000,
                "ju",
                listOf(PokemonTypeSlot(1, PokemonType("fire"))),
                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites( PokemonApiResult.OfficialArtwork( "url"))),
                listOf(PokemonApiResult.PokemonStats(2,1,PokemonApiResult.PokemonStatName("nome"))),
                40,87
                )
        )
        detailsPokemonViewModel.loadPokemonDetails (4000)
        verify(observer).onChanged(
            PokemonDetails("ju",4000,"url", listOf(Pair("nome",2)),40,87, R.color.colorfire)
        )

    }

    @Test
    fun  `test loadPokemonDetails using PokemonRepository and poison pokemon type , show poison pokemon details` (){
        whenever(pokemonRepository.getPokemon(4000)).doReturn(
            PokemonApiResult(
                4000,
                "ju",
                listOf(PokemonTypeSlot(1, PokemonType("poison"))),
                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites( PokemonApiResult.OfficialArtwork( "url"))),
                listOf(PokemonApiResult.PokemonStats(2,1,PokemonApiResult.PokemonStatName("nome"))),
                40,87
            )
        )
        detailsPokemonViewModel.loadPokemonDetails (4000)
        verify(observer).onChanged(
            PokemonDetails("ju",4000,"url", listOf(Pair("nome",2)),40,87, R.color.colorpoison)
        )

    }





}