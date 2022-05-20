package com.example.pokemon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.R
import com.example.pokemon.UseCase.ExibirListaDePokemons
import java.lang.IndexOutOfBoundsException


class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<PokemonItem?>>()
    private fun getcolortype(name: String): Int {
        return when (name) {
            "fire" -> R.color.colorfire
            "poison" -> R.color.colorpoison
            "flying" -> R.color.colorflying
            "water" -> R.color.colorwater
            "grass" -> R.color.colorgrass
            "bug" -> R.color.colorbug
            "normal" -> R.color.colornormal
            "electric" -> R.color.colorelectric
            "ground" -> R.color.colorground
            "fairy" -> R.color.colorfairy
            "fighting" -> R.color.colorfighting
            "psychic" -> R.color.colorpsychic
            "rock" -> R.color.colorrock
            "ice" -> R.color.colorice
            "ghost" -> R.color.colorghost
            "dragon" -> R.color.colordragon
            "dark" -> R.color.colordark
            else -> R.color.gelo
        }
    }

    fun loadPokemons() {
        val usecase = ExibirListaDePokemons()
        Thread(Runnable {


            pokemons.postValue(usecase.getPokemons()?.map {
                PokemonItem(
                    it.imageUrl,
                    it.name.replaceFirstChar { it.uppercase() },
                "Nº ${it.number.toString().padStart(3, '0')}",
                it.types[0].name,
                getcolortype(it.types[0].name),
                try {
                    it.types[1].name

                }catch (e: IndexOutOfBoundsException){null},
                    try {
                        getcolortype(it.types[1].name)

                    }catch (e: IndexOutOfBoundsException){null})
            })


        }).start()

    }
}








