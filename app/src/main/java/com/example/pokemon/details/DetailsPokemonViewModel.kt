package com.example.pokemon.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.ColorType
import com.example.pokemon.api.PokemonRepository


class DetailsPokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    var pokemonDetails = MutableLiveData<PokemonDetails>()

    fun loadPokemonDetails(number: Int) {

        val pokemon = pokemonRepository.getPokemon(number)


        pokemon?.let {
            val details = PokemonDetails(
                pokemon.name ,
                pokemon.id ,
                pokemon.sprites.other.officialArtwork.front_default ,
                pokemon.stats.map {
                    Pair(it.stat.name, it.base_stat)

                } ,
                pokemon.height ,
                pokemon.weight ,
                ColorType.getcolortype(pokemon.types[0].type.name)

            )
            pokemonDetails.postValue(details)


        }

    }




}


