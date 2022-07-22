package com.example.pokemon.compare

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.api.PokemonRepository


class ComparePokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    var pokemonCompare = MutableLiveData<PokemonCompare>()

    fun comparePokemon(num: Int, num2: Int) {

        val pokemonCompareLeft = pokemonRepository.getPokemon(num)
        val pokemonCompareRight = pokemonRepository.getPokemon(num2)


        val compare = PokemonCompare(
            pokemonCompareLeft?.sprites?.other?.officialArtwork?.front_default ?: "",
            pokemonCompareRight?.sprites?.other?.officialArtwork?.front_default?: "",
            pokemonCompareLeft?.name?: "",
            pokemonCompareRight?.name ?: "",




        )

        pokemonCompare.postValue(compare)

    }


}


