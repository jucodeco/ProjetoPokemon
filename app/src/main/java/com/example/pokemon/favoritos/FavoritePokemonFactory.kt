package com.example.pokemon.favoritos

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.favoritos.room.FavoriteRepository
import com.example.pokemon.favoritos.usecase.ExibirListaDePokemonsFavoritos
import java.security.AccessControlContext

@Suppress("UNCHECKED_CAST")
class FavoritePokemonFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritePokemonViewModel(ExibirListaDePokemonsFavoritos(FavoriteRepository(context), PokemonRepository)) as T
    }
}