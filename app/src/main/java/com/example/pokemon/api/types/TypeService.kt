package com.example.pokemon.api.types



import com.example.pokemon.api.model.TypeResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TypeService {

    @GET("type/{type}")
    fun getType(@Path("type") type: String): Call<TypeResult>


}