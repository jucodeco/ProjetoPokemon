package com.example.pokemon.compare

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.ColorType
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.types.TypeRepository
import com.example.pokemon.details.PokemonDetailsType
import java.lang.IndexOutOfBoundsException


class ComparePokemonViewModel(private val pokemonRepository: PokemonRepository, private val typeRepository: TypeRepository) : ViewModel() {

    var pokemonCompare = MutableLiveData<PokemonCompare>()

    fun comparePokemon(num: Int, num2: Int) {

        val pokemonCompareLeft = pokemonRepository.getPokemon(num)
        val pokemonCompareRight = pokemonRepository.getPokemon(num2)
        val typeLeft = typeRepository.typePokemon(pokemonCompareLeft.types[0].type.name)
        val typeRight = typeRepository.typePokemon(pokemonCompareRight.types[0].type.name)


        val compare = PokemonCompare(
            pokemonCompareLeft.sprites.other.officialArtwork.front_default,
            pokemonCompareRight.sprites.other.officialArtwork.front_default,
            pokemonCompareLeft.name.replaceFirstChar { it.uppercase() },
            pokemonCompareRight.name.replaceFirstChar { it.uppercase() },
            pokemonCompareRight.types.get(0).type.name,
            try {
                pokemonCompareRight.types.get(1).type.name
            } catch (e: IndexOutOfBoundsException) {
                null
            },
            pokemonCompareLeft?.types?.get(0)?.type?.name ?: "",

            try {
                pokemonCompareLeft?.types?.get(1)?.type?.name ?: ""
            } catch (e: IndexOutOfBoundsException) {
                null
            },
            ColorType.getcolortype(pokemonCompareRight?.types?.get(0)?.type?.name ?: ""),
            try {
                ColorType.getcolortype(pokemonCompareRight?.types?.get(1)?.type?.name ?: "")

            } catch (e: IndexOutOfBoundsException) {
                null

            },
            ColorType.getcolortype(pokemonCompareLeft?.types?.get(0)?.type?.name),
            try {
                ColorType.getcolortype(pokemonCompareLeft.types.get(1).type.name)

            } catch (e: IndexOutOfBoundsException) {
                null

            },

            pokemonCompareLeft.stats.map {

                Pair(it.stat.name, it.base_stat)
            },
            pokemonCompareRight.stats.map {
                Pair(it.stat.name, it.base_stat)
            },


            


        )


        pokemonCompare.postValue(compare)


    }
}


