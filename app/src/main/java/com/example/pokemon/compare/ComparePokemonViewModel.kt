package com.example.pokemon.compare

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.api.PokemonRepository
import java.lang.IndexOutOfBoundsException


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
            pokemonCompareRight?.types?.get(0)?.type?.name ?: "",
            try {
                pokemonCompareRight?.types?.get(1)?.type?.name ?: ""
            }catch (e: IndexOutOfBoundsException) {
                null
            },
            pokemonCompareLeft?.types?.get(0)?.type?.name ?: "",

            try {
                pokemonCompareLeft?.types?.get(1)?.type?.name ?: ""
            }catch (e: IndexOutOfBoundsException) {
                null
            },

            )

        pokemonCompare.postValue(compare)

    }


}


