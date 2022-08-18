package com.example.pokemon.lista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.api.PokemonRepositoryImpl
import com.example.pokemon.compare.CompareActivity
import com.example.pokemon.compare.CompareActivity.Companion.COMPARE_POKEMON_LEFT
import com.example.pokemon.compare.CompareActivity.Companion.COMPARE_POKEMON_RIGHT

import com.example.pokemon.details.DetailsActivity
import com.example.pokemon.details.DetailsActivity.Companion.EXTRA_POKEMON_NUMBER
import com.example.pokemon.favoritos.room.FavoriteRepositoryImpl
import com.example.pokemon.lista.usecase.ViewPokemonList
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment() : Fragment(R.layout.fragment_list) {
    private lateinit var viewModel: PokemonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_catalog_fragment)

        val progress = view.findViewById<ProgressBar>(R.id.progress_bar_catalog_fragment)

        val fab = view.findViewById<FloatingActionButton>(R.id.floating_action_button_poke)
        context?.let {
        viewModel = PokemonViewModel(
            FavoriteRepositoryImpl(it),
            ViewPokemonList(PokemonRepositoryImpl(it.cacheDir))
        )
        }
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = PokemonAdapter(onAddFavoriteListener, onClickRemoveFavorite, onItemClick = ::onPokemonClick, onComparePokemon = :: onComparePokemon)
        recyclerView.adapter = adapter
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {

            adapter.update(it)

            progress.visibility = View.GONE

        })

        fab.setOnClickListener {
            adapter.toggleCheckbox ()
        }
        viewModel.loadPokemons()

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










