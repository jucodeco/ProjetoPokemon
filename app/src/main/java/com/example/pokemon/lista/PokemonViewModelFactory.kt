package com.example.pokemon.lista

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.api.PokemonRepositoryImpl
import com.example.pokemon.favoritos.room.FavoriteRepositoryImpl
import com.example.pokemon.lista.usecase.ViewPokemonList


@Suppress("UNCHECKED_CAST")
class PokemonViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonViewModel(
            FavoriteRepositoryImpl(context),
            ViewPokemonList(PokemonRepositoryImpl(context.cacheDir))
        ) as T
    }

}