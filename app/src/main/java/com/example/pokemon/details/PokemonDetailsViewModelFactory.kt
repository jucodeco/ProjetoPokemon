package com.example.pokemon.details

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.api.PokemonRepositoryImpl

class PokemonDetailsViewModelFactory (private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsPokemonViewModel(
           PokemonRepositoryImpl(context.cacheDir),

        ) as T
    }

}