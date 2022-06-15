package com.example.pokemon.favoritos.room

import androidx.room.*


@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(guest: PokemonFavorite): Long

    @Delete
    fun delete(guest: PokemonFavorite)

    @Query("SELECT * FROM Favoritos")
    fun getInvited(): List<PokemonFavorite>

}