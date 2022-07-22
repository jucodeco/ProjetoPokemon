package com.example.pokemon.compare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.api.PokemonRepositoryImpl


class PokemonCompareViewModelFactory(private val context: CompareActivity) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ComparePokemonViewModel(
            PokemonRepositoryImpl(context.cacheDir)


        ) as T
    }

}