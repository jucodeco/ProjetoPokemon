//package com.example.pokemon.compare
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.pokemon.api.PokemonRepositoryImpl
//import com.example.pokemon.api.types.TypeRepository
//import com.example.pokemon.api.types.TypeRepositoryImpl
//
//
//class PokemonCompareViewModelFactory(private val context: CompareActivity) : ViewModelProvider.Factory {
//
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return ComparePokemonViewModel(
//            PokemonRepositoryImpl(context.cacheDir),
//            TypeRepositoryImpl(context.cacheDir)
//
//
//        ) as T
//    }
//
//}