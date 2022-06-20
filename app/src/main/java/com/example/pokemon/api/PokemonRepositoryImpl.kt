package com.example.pokemon.api

import com.example.pokemon.api.model.PokemonApiResult
import com.example.pokemon.api.model.PokemonsApiResult
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class PokemonRepositoryImpl (private val cachedir: File) : PokemonRepository {

    private val service: PokemonService by lazy {
        val SIZE_OF_CACHE = (10 * 1024 * 1024).toLong() // 10 MiB

        val cache = Cache(File(cachedir, "http"), SIZE_OF_CACHE)
        val client = OkHttpClient.Builder().cache(cache).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(PokemonService::class.java)
    }





    override fun listPokemons(limit: Int): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()
    }


    override fun getPokemon(number: Int): PokemonApiResult? {
        val call = service.getPokemon(number)

        return call.execute().body()
    }




    }




