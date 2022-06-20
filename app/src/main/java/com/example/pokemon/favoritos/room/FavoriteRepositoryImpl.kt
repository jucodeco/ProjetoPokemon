package com.example.pokemon.favoritos.room

import android.content.Context

class FavoriteRepositoryImpl (context: Context) : FavoriteRepository {

    // Acesso ao banco de dados
    private val mDataBase = AppDatabase.getDatabase(context).getfavoriteDAO()

    /**
     * Insere favoritos
     */
    override fun save(number: Int): Boolean {
        return mDataBase.save(PokemonFavorite(number)) > 0
    }

    /**
     * Faz a listagem de todos os favoritos
     */
    override fun getAll(): List<PokemonFavorite> {
        return mDataBase.getInvited()
    }

    /**
     * Remove favoritos
     */
    override fun delete(guest: PokemonFavorite) {
        mDataBase.delete(guest)

    }

}