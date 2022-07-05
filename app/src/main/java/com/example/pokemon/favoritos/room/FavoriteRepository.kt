package com.example.pokemon.favoritos.room




interface FavoriteRepository {

     fun save(number: Int): Boolean
     fun delete(guest: PokemonFavorite)
     fun getAll(): List<PokemonFavorite>



}
