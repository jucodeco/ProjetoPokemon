package com.example.pokemon.favoritos

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.pokemon.api.model.PokemonRepository
import com.example.pokemon.favoritos.room.FavoriteRepository
import com.example.pokemon.favoritos.usecase.ViewListFavoritePokemons

@Suppress("UNCHECKED_CAST")
class FavoritePokemonFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritePokemonViewModel(ViewListFavoritePokemons(FavoriteRepository(context), PokemonRepository)) as T
    }
}