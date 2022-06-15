package com.example.pokemon.api.model

import com.example.pokemon.api.PokemonService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object PokemonRepository {
    val service: PokemonService by lazy {
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
    lateinit var cachedir: File


    fun listPokemons(limit: Int = 151): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()
    }

    fun getPokemon(number: Int): PokemonApiResult? {
        val call = service.getPokemon(number)

        return call.execute().body()
    }
}
