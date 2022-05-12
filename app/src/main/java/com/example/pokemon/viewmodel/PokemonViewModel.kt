package com.example.pokemon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.model.PokemonApiResult
import com.example.pokemon.model.PokemonsApiResult


class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon?>>()


    fun loadPokemons() {

        Thread(Runnable {


            val pokemonsApiResult: PokemonsApiResult? = PokemonRepository.listPokemons()

            Log.d("Pokemon", pokemonsApiResult.toString())
            pokemonsApiResult?.results?.let {


                pokemons.postValue(it.map { pokemonResult ->
                    val number = pokemonResult.url
                        .replace("https://pokeapi.co/api/v2/pokemon/", "")
                        .replace("/", "").toInt()

                    val pokemonApiResult = PokemonRepository.getPokemon(number)

                    pokemonApiResult?.let {
                        Pokemon(
                            pokemonApiResult.id,
                            pokemonApiResult.name,
                            pokemonApiResult.types.map { type ->
                                type.type
                            }
                        )
                    }

                })

            }
        }).start()
    }
}








