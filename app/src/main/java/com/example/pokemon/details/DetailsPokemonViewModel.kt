package com.example.pokemon.details


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.ColorType
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.types.TypeRepository


class DetailsPokemonViewModel(private val pokemonRepository: PokemonRepository, private val typeRepository: TypeRepository) : ViewModel() {

    var pokemonDetails = MutableLiveData<PokemonDetails>()


    fun loadPokemonDetails(number: Int) {

        val pokemon = pokemonRepository.getPokemon(number)

        pokemon.let {
            val type = typeRepository.typePokemon(pokemon.types[0].type.name)
            type?.let {
                val weaknessTypeList = mutableListOf<PokemonDetailsType>()

                val typeList = type.damage_relations.double_damage_from.map {
                    PokemonDetailsType(it.name, ColorType.getcolortype(it.name))
                }
                weaknessTypeList.addAll(typeList)

                val typeResistance = type.damage_relations.half_damage_from.map {
                    PokemonDetailsType(it.name,ColorType.getcolortype(it.name))
                }

                if (pokemon.types.size == 2) {
                    val secondType = typeRepository.typePokemon(pokemon.types[1].type.name)
                    secondType?.let {
                        val secondList = secondType.damage_relations.double_damage_from.map {
                            PokemonDetailsType(it.name, ColorType.getcolortype(it.name))

                        }
                        weaknessTypeList.addAll(secondList)
                    }

                }
                val details = PokemonDetails(
                    pokemon.name.replaceFirstChar { it.uppercase() },
                    pokemon.id,
                    pokemon.sprites.other.officialArtwork.front_default,
                    pokemon.stats.map {
                        Pair(it.stat.name, it.base_stat)

                    },
                    pokemon.height,
                    pokemon.weight,
                    ColorType.getcolortype(pokemon.types[0].type.name),
                weaknessTypeList, typeResistance

                )
                pokemonDetails.postValue(details)


            }
        }


    }

}







