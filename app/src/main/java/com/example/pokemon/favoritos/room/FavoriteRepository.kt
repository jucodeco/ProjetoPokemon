package com.example.pokemon.favoritos.room




interface FavoriteRepository {

     fun save(number: Int): Boolean {
        TODO("Not yet implemented")
    }
     fun delete(guest: PokemonFavorite) {}
     fun getAll(): List<PokemonFavorite> {
        TODO("Not yet implemented")
    }


}
