package com.example.pokemon.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.api.PokemonRepositoryImpl
import com.example.pokemon.api.types.TypeRepository
import com.example.pokemon.api.types.TypeRepositoryImpl
import com.example.pokemon.lista.usecase.ViewPokemonList

class PokemonDetailsViewModelFactory(private val context: DetailsActivity) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsPokemonViewModel(
            PokemonRepositoryImpl(context.cacheDir),
            TypeRepositoryImpl((context.cacheDir))

          ) as T
    }

}