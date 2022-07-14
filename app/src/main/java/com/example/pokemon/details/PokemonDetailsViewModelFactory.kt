package com.example.pokemon.details

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.api.PokemonRepositoryImpl
import com.example.pokemon.details.fragment.AboutFragment

class PokemonDetailsViewModelFactory(private val context: DetailsActivity) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsPokemonViewModel(
            PokemonRepositoryImpl(context.cacheDir),

            ) as T
    }

}