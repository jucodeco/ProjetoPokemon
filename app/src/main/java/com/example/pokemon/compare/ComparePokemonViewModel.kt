package com.example.pokemon.compare

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.ColorType
import com.example.pokemon.R
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.types.TypeRepository
import java.lang.IndexOutOfBoundsException


class ComparePokemonViewModel(private val pokemonRepository: PokemonRepository, private val typeRepository: TypeRepository) : ViewModel() {

    var pokemonCompare = MutableLiveData<PokemonCompare>()

    fun comparePokemon(num: Int, num2: Int) {

        val pokemonCompareLeft = pokemonRepository.getPokemon(num)
        val pokemonCompareRight = pokemonRepository.getPokemon(num2)


        pokemonCompareLeft.let {
            val type = typeRepository.typePokemon(pokemonCompareLeft.types[0].type.name)


            type?.let {

                val typeWeakLeft = type.damage_relations.double_damage_from.filter { type ->
                    pokemonCompareRight.types.any {
                        it.type.name == type.name
                    }

                }.map {
                    PokemonCompareType(it.name, ColorType.getcolortype(it.name))

                }

                pokemonCompareRight.let {
                    val typeResistanceLeft = type.damage_relations.half_damage_from.filter { type ->

                        pokemonCompareRight.types.any {
                            it.type.name == type.name
                        }

                    }.map {
                        PokemonCompareType(it.name, ColorType.getcolortype(it.name))
                    }


                    val typeResistanceRight = type.damage_relations.half_damage_from.filter { type ->

                        pokemonCompareLeft.types.any {
                            it.type.name == type.name
                        }

                    }.map {
                        PokemonCompareType(it.name, ColorType.getcolortype(it.name))
                    }


                    val typeWeakRight = type.damage_relations.double_damage_from.filter { type ->
                        pokemonCompareLeft.types.any {
                            it.type.name == type.name
                        }


                    }.map {
                        PokemonCompareType(it.name, ColorType.getcolortype(it.name))

                    }

                    val pokemonLeftHpValue = pokemonCompareLeft.stats.firstOrNull {
                        it.stat.name == "hp"
                    }?.base_stat ?: 0

                    val pokemonRightHpValue = pokemonCompareRight.stats.firstOrNull {
                        it.stat.name == "hp"
                    }?.base_stat ?: 0

                    val pokemonLeftAttackValue = pokemonCompareLeft.stats.firstOrNull {
                        it.stat.name == "attack"
                    }?.base_stat ?: 0

                    val pokemonRightAttackValue = pokemonCompareRight.stats.firstOrNull {
                        it.stat.name == "attack"
                    }?.base_stat ?: 0

                    val pokemonLeftDefenseValue = pokemonCompareLeft.stats.firstOrNull {
                        it.stat.name == "defense"
                    }?.base_stat ?: 0

                    val pokemonRightDefenseValue = pokemonCompareRight.stats.firstOrNull {
                        it.stat.name == "defense"
                    }?.base_stat ?: 0

                    val pokemonLeftSpArkValue = pokemonCompareLeft.stats.firstOrNull {
                        it.stat.name == "special-attack"
                    }?.base_stat ?: 0

                    val pokemonRightSpArkValue = pokemonCompareRight.stats.firstOrNull {
                        it.stat.name == "special-attack"
                    }?.base_stat ?: 0

                    val pokemonLeftSpDefValue = pokemonCompareLeft.stats.firstOrNull {
                        it.stat.name == "special-defense"
                    }?.base_stat ?: 0

                    val pokemonRightSpDefValue = pokemonCompareRight.stats.firstOrNull {
                        it.stat.name == "special-defense"
                    }?.base_stat ?: 0

                    val pokemonLeftSpeedValue = pokemonCompareLeft.stats.firstOrNull {
                        it.stat.name == "speed"
                    }?.base_stat ?: 0

                    val pokemonRightSpeedValue = pokemonCompareRight.stats.firstOrNull {
                        it.stat.name == "speed"
                    }?.base_stat ?: 0


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

                        typeResistanceLeft,

                        typeResistanceRight,
                        typeWeakLeft,
                        typeWeakRight,
                        getStatsIcon(pokemonLeftHpValue, pokemonRightHpValue),

                        getStatsIcon(pokemonRightHpValue, pokemonLeftHpValue),

                        getStatsIcon(pokemonLeftAttackValue, pokemonRightAttackValue),

                        getStatsIcon(pokemonRightAttackValue, pokemonLeftAttackValue),

                        getStatsIcon(pokemonLeftDefenseValue, pokemonRightDefenseValue),

                        getStatsIcon(pokemonRightDefenseValue, pokemonLeftDefenseValue),

                        getStatsIcon(pokemonLeftSpArkValue, pokemonRightSpArkValue),

                        getStatsIcon(pokemonRightSpArkValue, pokemonLeftSpArkValue),

                        getStatsIcon(pokemonLeftSpDefValue, pokemonRightSpDefValue),

                        getStatsIcon(pokemonRightSpDefValue, pokemonLeftSpDefValue),

                        getStatsIcon(pokemonLeftSpeedValue, pokemonRightSpeedValue),

                        getStatsIcon(pokemonRightSpeedValue, pokemonLeftSpeedValue),
                        R.drawable.ic_hp_red,
                        R.drawable.ic_hp_red,
                        R.drawable.ic_attack,
                        R.drawable.ic_attack,
                        R.drawable.ic_defense,
                        R.drawable.ic_defense,
                        R.drawable.ic_spattack,
                        R.drawable.ic_spattack,
                        R.drawable.ic_spdef,
                        R.drawable.ic_spdef,
                        R.drawable.ic_speed,
                        R.drawable.ic_speed


                    )


                    pokemonCompare.postValue(compare)


                }


            }
        }
    }

    private fun getStatsIcon(pokemonStatsValueFirst: Int, pokemonStatsValueSecond: Int) = when {
        pokemonStatsValueFirst > pokemonStatsValueSecond -> R.drawable.ic_compare_green
        pokemonStatsValueFirst < pokemonStatsValueSecond -> R.drawable.ic_compare_red
        else -> R.drawable.ic_baseline_compare_arrows_24

    }


}



