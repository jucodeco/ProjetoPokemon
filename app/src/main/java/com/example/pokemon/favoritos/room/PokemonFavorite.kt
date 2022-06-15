package com.example.pokemon.favoritos.room


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "Favoritos")
class PokemonFavorite(
    @PrimaryKey(autoGenerate = false)
    var number: Int
) {

}