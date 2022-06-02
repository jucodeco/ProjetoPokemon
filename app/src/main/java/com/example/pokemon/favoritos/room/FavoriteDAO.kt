package com.example.pokemon.favoritos.room

import androidx.room.*


@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(guest: PokemonFavorito): Long

    @Delete
    fun delete(guest: PokemonFavorito)

    @Query("SELECT * FROM Favoritos")
    fun getInvited(): List<PokemonFavorito>

}