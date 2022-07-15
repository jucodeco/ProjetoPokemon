package com.example.pokemon.api.types


import com.example.pokemon.api.model.TypeResult
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class TypeRepositoryImpl (private val cacheDir: File) : TypeRepository {

    private val serviceType: TypeService by lazy {
        val sizeOf = (10 * 1024 * 1024).toLong() // 10 MiB

        val cache = Cache(File(cacheDir, "http"), sizeOf)
        val client = OkHttpClient.Builder().cache(cache).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(TypeService::class.java)
    }

    override fun typePokemon(type:String): TypeResult? {
        val typeCall = serviceType.getType(type)

        return typeCall.execute().body()
    }

}