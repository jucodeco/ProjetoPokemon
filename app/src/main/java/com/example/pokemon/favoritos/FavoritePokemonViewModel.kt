package com.example.pokemon.favoritos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.ColorType
import com.example.pokemon.favoritos.usecase.ViewListFavoritePokemons
import com.example.pokemon.lista.PokemonItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IndexOutOfBoundsException

class FavoritePokemonViewModel(private val usecase: ViewListFavoritePokemons) : ViewModel() {
    fun loadPokemons() = viewModelScope.launch(Dispatchers.IO) {
        pokemons.postValue(usecase.get().map {
            PokemonItem(
                it.imageUrl,
                it.name.replaceFirstChar { it.uppercase() },
                "Nº ${it.number.toString().padStart(3, '0')}",
                it.types[0].name,
                ColorType.getcolortype(it.types[0].name),
                try {
                    it.types[1].name

                } catch (e: IndexOutOfBoundsException) {
                    null
                },
                try {
                    ColorType.getcolortype(it.types[1].name)

                } catch (e: IndexOutOfBoundsException) {
                    null
                },
                it.number,
                isfav = true


            )
        })


    }

    var pokemons = MutableLiveData<List<PokemonItem?>>()
}
