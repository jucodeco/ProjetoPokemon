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


        pokemonCompareLeft.let {
            val type = typeRepository.typePokemon(pokemonCompareLeft.types[0].type.name)
            type?.let {
                val typeSecond = typeRepository.typePokemon(pokemonCompareLeft.types[1].type.name)
                typeSecond?.let {
                    val typeWeakLeft = typeSecond.damage_relations.double_damage_from.filter { type ->

                        pokemonCompareLeft.types.any {
                            it.type.name == type.name
                        }
                    }.map {
                        PokemonCompareType(it.name, ColorType.getcolortype(it.name))
                    }


                    pokemonCompareRight.let {
                        val typeResistance = type.damage_relations.half_damage_from.filter { type ->

                            pokemonCompareRight.types.any {
                                it.type.name == type.name
                            }

                        }.map {
                            PokemonCompareType(it.name, ColorType.getcolortype(it.name))
                        }
                        val typeWeakRight = typeSecond.damage_relations.double_damage_from.filter { type ->

                            pokemonCompareRight.types.any {
                                it.type.name == type.name
                            }

                        }.map {
                            PokemonCompareType(it.name, ColorType.getcolortype(it.name))
                        }

                        val type = typeRepository.typePokemon(pokemonCompareRight.types[0].type.name)
                        type?.let {

                            val typeResistanceRight = type.damage_relations.half_damage_from.filter { type ->

                                pokemonCompareLeft.types.any {
                                    it.type.name == type.name
                                }

                            }.map {
                                PokemonCompareType(it.name, ColorType.getcolortype(it.name))
                            }

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
                                pokemonCompareLeft.types.get(0).type.name,

                                try {
                                    pokemonCompareLeft.types.get(1).type.name
                                } catch (e: IndexOutOfBoundsException) {
                                    null
                                },
                                ColorType.getcolortype(pokemonCompareRight.types.get(0).type.name),
                                try {
                                    ColorType.getcolortype(pokemonCompareRight.types.get(1).type.name)

                                } catch (e: IndexOutOfBoundsException) {
                                    null

                                },
                                ColorType.getcolortype(pokemonCompareLeft.types.get(0).type.name),
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

                                typeResistance,

                                typeResistanceRight,

                                typeWeakLeft,

                                typeWeakRight


                            )


                            pokemonCompare.postValue(compare)


                        }
                    }
                }
            }
        }
    }
}


