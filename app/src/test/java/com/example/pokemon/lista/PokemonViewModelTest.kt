package com.example.pokemon.lista


import com.example.pokemon.favoritos.room.FavoriteRepository
import com.example.pokemon.favoritos.room.PokemonFavorite
import com.example.pokemon.lista.usecase.ViewPokemonList
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.PokemonType
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before


class PokemonViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val observer = mock<Observer<List<PokemonItem?>>>()
    private val favoriteRepository = mock<FavoriteRepository>()
    private val viewPokemonList = mock<ViewPokemonList>()
    private val viewModel = PokemonViewModel(favoriteRepository = favoriteRepository, usecase = viewPokemonList)

    @Before
    fun setup() {
        viewModel.pokemons.observeForever(observer)
    }

    @Test
    fun `test when i call addFavorite make sure it's saving to favoriteRepository`() {
        viewModel.addFavorite(3)
        verify(favoriteRepository).save(3)
    }

    @Test
    fun `test when i call removeFavorite make sure it's delete to favoriteRepository`() {
        viewModel.removeFavorite(3)
        verify(favoriteRepository).delete(PokemonFavorite(3))
    }

    @Test
    fun `when will my pokemon list then appear on the screen `() {
        whenever(favoriteRepository.getAll()).doReturn(listOf(PokemonFavorite(1)))
        whenever(viewPokemonList.getPokemons()).doReturn(
            listOf(
                Pokemon(1, "pokemonname", listOf(PokemonType("flying")), "imagem"),
                Pokemon(2, "pokemon2", listOf(PokemonType("flying"), PokemonType("poison")), "imagem"),
            )
        )
        viewModel.loadPokemons()
        verify(observer).onChanged(
            listOf(
                PokemonItem("imagem", "Pokemonname", "Nº 001", "flying", R.color.colorflying, null, null, 1, true),
                PokemonItem("imagem", "Pokemon2", "Nº 002", "flying", R.color.colorflying, "poison", R.color.colorpoison, 2, false)
            )
        )

    }

}


