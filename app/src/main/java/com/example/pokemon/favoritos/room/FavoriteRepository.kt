package com.example.pokemon.favoritos.room
import android.content.Context

class FavoriteRepository(context: Context) {

    // Acesso ao banco de dados
    private val mDataBase = AppDatabase.getDatabase(context).getfavoriteDAO()

    /**
     * Insere favoritos
     */
    fun save(number: Int): Boolean {
        return mDataBase.save(PokemonFavorite(number)) > 0
    }

    /**
     * Faz a listagem de todos os favoritos
     */
    fun getAll(): List<PokemonFavorite> {
        return mDataBase.getInvited()
    }

    /**
     * Remove favoritos
     */
    fun delete(guest: PokemonFavorite) {
        mDataBase.delete(guest)

    }
}
