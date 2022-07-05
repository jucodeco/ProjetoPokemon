package com.example.pokemon.favoritos.usecase

import com.example.pokemon.domain.Pokemon


interface ViewListFavoritePokemons {

    fun get(): List<Pokemon>
}

