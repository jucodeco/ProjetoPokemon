package com.example.pokemon.lista

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.details.DetailsActivity
import com.example.pokemon.details.DetailsActivity.Companion.EXTRA_POKEMON_NUMBER


class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var viewModel: PokemonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_catalog_fragment)

        val progress = view.findViewById<ProgressBar>(R.id.progress_bar_catalog_fragment)

        viewModel = ViewModelProvider(this, PokemonViewModelFactory(view.context))
            .get(PokemonViewModel::class.java)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = PokemonAdapter(onAddFavoriteListener,onClickRemoveFavorite,onItemClick = :: onPokemonClick)
        recyclerView.adapter = adapter
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {

            adapter.update(it)

            progress.visibility = View.GONE

        })
        Thread(Runnable {
            viewModel.loadPokemons()
        })
            .start()

    }

    private val onAddFavoriteListener by lazy {
        object : OnClickAddFavorite {
            override fun onAddFavorite(pokemonItem: PokemonItem) {
                viewModel.addFavorite (pokemonItem.id)
                Thread(Runnable {
                    viewModel.loadPokemons()
                })
                    .start()
            }

        }

    }
    private val onClickRemoveFavorite by lazy {
        object : OnClickRemoveFavorite {
            override fun onClickRemoveFavorite(pokemonItem: PokemonItem) {
                viewModel.
                removeFavorite (pokemonItem.id)
                Thread(Runnable {
                    viewModel.loadPokemons()
                })
                    .start()
            }

        }

    }

    private fun onPokemonClick (pokemonItem: PokemonItem) {
        val intent = Intent (context, DetailsActivity::class.java).apply {
            putExtra(EXTRA_POKEMON_NUMBER, pokemonItem.id)
        }
        startActivity(intent)
    }



}





