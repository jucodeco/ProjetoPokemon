package com.example.pokemon.lista

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.pokemon.api.PokemonRepositoryImpl
import com.example.pokemon.compare.CompareActivity
import com.example.pokemon.compare.CompareActivity.Companion.COMPARE_POKEMON_LEFT
import com.example.pokemon.compare.CompareActivity.Companion.COMPARE_POKEMON_RIGHT

import com.example.pokemon.details.DetailsActivity
import com.example.pokemon.details.DetailsActivity.Companion.EXTRA_POKEMON_NUMBER
import com.example.pokemon.favoritos.room.FavoriteRepositoryImpl
import com.example.pokemon.lista.usecase.ViewPokemonList
import com.example.pokemon.lista.theme.PokemonTheme

class ListFragmentCompose() : Fragment() {
    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext())
        context?.let {
            viewModel = PokemonViewModel(
                FavoriteRepositoryImpl(it),
                ViewPokemonList(PokemonRepositoryImpl(it.cacheDir))
            )
        }
        view.apply {
            setContent {
                PokemonTheme {
                    viewModel.pokemons.observeAsState().value?.let {
                        PokemonListScreen(it, ::onPokemonClick, viewModel,::onComparePokemon, onAddFavoriteListener, onClickRemoveFavorite, )

                    }

                }
            }


        }

        viewModel.loadPokemons()
        return view
    }



    private val onAddFavoriteListener by lazy {
        object : OnClickAddFavorite {
            override fun onAddFavorite(pokemonItem: PokemonItem) {
                viewModel.addFavorite(pokemonItem.id)

                viewModel.loadPokemons()

            }

        }

    }
    private val onClickRemoveFavorite by lazy {
        object : OnClickRemoveFavorite {
            override fun onClickRemoveFavorite(pokemonItem: PokemonItem) {
                viewModel.removeFavorite(pokemonItem.id)

                viewModel.loadPokemons()

            }

        }

    }


    private fun onPokemonClick(pokemonItem: PokemonItem) {
        val intent = Intent(context, DetailsActivity::class.java).apply {
            putExtra(EXTRA_POKEMON_NUMBER, pokemonItem.id)
        }
        startActivity(intent)
    }

    private fun onComparePokemon (pokemonNumberLeft: Int, pokemonNumberRight: Int){
        val intent = Intent(context, CompareActivity::class.java).apply {
            putExtra(COMPARE_POKEMON_LEFT, pokemonNumberLeft)
            putExtra(COMPARE_POKEMON_RIGHT, pokemonNumberRight)
        }
        startActivity(intent)
    }




    }










