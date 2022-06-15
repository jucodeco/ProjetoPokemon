package com.example.pokemon.favoritos.usecase


import com.example.pokemon.api.model.PokemonRepository
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.favoritos.room.FavoriteRepository


class ViewListFavoritePokemons
    (private val favoriteRepository: FavoriteRepository, private val pokemonRepository: PokemonRepository ) {

            fun get(): List<Pokemon> {
               val favorites = favoriteRepository.getAll()
                return favorites.map { favorite ->
                    val pokemonApiResult = pokemonRepository.getPokemon(favorite.number)
                    Pokemon(
                        pokemonApiResult?.id ?: 0,
                        pokemonApiResult?.name ?: "",
                        pokemonApiResult?.types?.map { type ->
                            type.type
                        } ?: emptyList(),
                        pokemonApiResult?.sprites?.other?.officialArtwork?.front_default?:""
                    )
                }
            }



}




