package com.example.pokemon.favoritos

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider



import com.example.pokemon.favoritos.usecase.ViewListFavoritePokemons
import com.example.pokemon.api.PokemonRepositoryImpl
import com.example.pokemon.favoritos.room.FavoriteRepositoryImpl

@Suppress("UNCHECKED_CAST")
class FavoritePokemonFactory(private val context: Context ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritePokemonViewModel(ViewListFavoritePokemons(
            FavoriteRepositoryImpl(context),
            PokemonRepositoryImpl(context.cacheDir))) as T
    }
}