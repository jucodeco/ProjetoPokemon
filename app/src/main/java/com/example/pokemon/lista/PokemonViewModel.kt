package com.example.pokemon.lista

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.ColorType
import com.example.pokemon.favoritos.room.FavoriteRepository
import com.example.pokemon.favoritos.room.PokemonFavorite
import com.example.pokemon.lista.usecase.ViewPokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IndexOutOfBoundsException


class PokemonViewModel(
    private val favoriteRepository: FavoriteRepository,
    private val usecase: ViewPokemonList
) : ViewModel() {

    var pokemons = MutableLiveData<List<PokemonItem>>()

    fun loadPokemons() = viewModelScope.launch(Dispatchers.IO) {

        val favoritePokemons = favoriteRepository.getAll()


        pokemons.postValue(usecase.getPokemons()?.map {
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

                favoritePokemons.firstOrNull { pokemon -> pokemon.number == it.number } != null,

            )
        })


    }

    fun addFavorite(id: Int) {
        favoriteRepository.save(id)

    }

    fun removeFavorite(guest: Int) {
        favoriteRepository.delete(PokemonFavorite(guest))

    }


    fun calcDominantColor(drawble: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawble as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)



        }
    }










