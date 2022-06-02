package com.example.pokemon.lista

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R

class ListaFragment : Fragment(R.layout.fragment_lista) {


    private lateinit var viewModel: PokemonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvPokemons)

        val progress = view.findViewById<ProgressBar>(R.id.progress)

        viewModel = ViewModelProvider(this, PokemonViewModelFactory(view.context))
            .get(PokemonViewModel::class.java)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = PokemonAdapter(onAddFavoriteListener,onClickRemoveFavorite)
        recyclerView.adapter = adapter
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {

            adapter.update(it)

            progress.visibility = View.GONE

        })
        viewModel.loadPokemons()


    }

    private val onAddFavoriteListener by lazy {
        object : OnClickAddFavorite {
            override fun onAddFavorite(pokemonItem: PokemonItem) {
                viewModel.addFavorite (pokemonItem.id)
                viewModel.loadPokemons()
            }

        }

    }
    private val onClickRemoveFavorite by lazy {
        object : OnClickRemoveFavorite {
            override fun onClickRemoveFavorite(pokemonItem: PokemonItem) {
                viewModel.removefavorite (pokemonItem.id)
                viewModel.loadPokemons()
            }

        }

    }

}


