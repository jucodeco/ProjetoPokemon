package com.example.pokemon.favoritos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.PokemonType
import com.example.pokemon.favoritos.room.FavoriteRepository
import com.example.pokemon.favoritos.room.PokemonFavorite
import com.example.pokemon.favoritos.usecase.ViewListFavoritePokemons
import com.example.pokemon.lista.PokemonItem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class FavoritePokemonViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val viewListFavoritePokemons = mock<ViewListFavoritePokemons>()
    private val favoriteRepository = mock<FavoriteRepository>()
    private val viewModel = FavoritePokemonViewModel(usecase = viewListFavoritePokemons)
    private val observer = mock<Observer<List<PokemonItem?>>>()


    @Before
    fun setup() {
        viewModel.pokemons.observeForever(observer)
    }


    @Test
    fun `given my favorite repository when viewModel loads then my Favorite Pokemon will appear`(){
        whenever(favoriteRepository.getAll()).doReturn(listOf(PokemonFavorite(1)))
        whenever(viewListFavoritePokemons.get()).doReturn(listOf(
            Pokemon(1,"name 1", listOf(PokemonType("flying")),"url"),
            Pokemon(2, "name 2", listOf(PokemonType("electric"), PokemonType("water")), "url"),
        )
        )
        viewModel.loadPokemons()
       verify(observer).onChanged(
           listOf(
               PokemonItem("url","Name 1","Nº 001","flying",R.color.colorflying,null,null, 1,true),
               PokemonItem("url","Name 2","Nº 002","electric",R.color.colorelectric,"water",R.color.colorwater, 2,true)
           ))






    }



    }

