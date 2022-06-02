package com.example.pokemon.lista

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.favoritos.room.FavoriteRepository
import java.security.AccessControlContext

@Suppress("UNCHECKED_CAST")
class PokemonViewModelFactory (private val context:Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonViewModel(FavoriteRepository(context)  ) as T
    }
}