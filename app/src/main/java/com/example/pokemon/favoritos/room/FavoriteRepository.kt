package com.example.pokemon.favoritos.room
import android.content.Context

class FavoriteRepository(context: Context) {

    // Acesso ao banco de dados
    private val mDataBase = AppDatabase.getDatabase(context).getfavoriteDAO()

    /**
     * Insere favoritos
     */
    fun save(number: Int): Boolean {
        return mDataBase.save(PokemonFavorito(number)) > 0
    }

    /**
     * Faz a listagem de todos os favoritos
     */
    fun getAll(): List<PokemonFavorito> {
        return mDataBase.getInvited()
    }

    /**
     * Remove favoritos
     */
    fun delete(guest: PokemonFavorito) {
        mDataBase.delete(guest)

    }
}
