package com.example.pokemon.favoritos.usecase


import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.model.PokemonApiResult
import com.example.pokemon.api.model.PokemonResult
import com.example.pokemon.api.model.PokemonTypeSlot
import com.example.pokemon.api.model.PokemonsApiResult
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.domain.PokemonType
import com.example.pokemon.favoritos.room.FavoriteRepository
import com.example.pokemon.favoritos.room.PokemonFavorite
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Test


class ViewListFavoritePokemonsImplTest {


    private val favoriteRepository = mock<FavoriteRepository>()
    private val pokemonRepository = mock<PokemonRepository>()
    private val viewListFavoritePokemonsImpl = ViewListFavoritePokemonsImpl(favoriteRepository,pokemonRepository)

    @Test
    fun `given the Favorites Repository when my Pokemon Favorites list is called make sure the list is loaded`() {
        whenever(favoriteRepository.getAll()).doReturn(listOf(PokemonFavorite(1)))

        whenever(pokemonRepository.getPokemon(1)).doReturn(
            PokemonApiResult(
                1, "nome", listOf(PokemonTypeSlot(1, PokemonType("tipo"))),
                PokemonApiResult.Sprites(other = PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork(front_default = "http")))
            , emptyList(),0,0)
        )
        Assert.assertEquals(
            listOf(Pokemon(1, "nome", listOf(PokemonType("tipo")), "http")),

            viewListFavoritePokemonsImpl.get()

        )




    }



}