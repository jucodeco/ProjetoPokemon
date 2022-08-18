package com.example.pokemon.favoritos

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.pokemon.R
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.PokemonRepositoryImpl
import com.example.pokemon.favoritos.room.FavoriteRepositoryImpl
import com.example.pokemon.favoritos.usecase.ViewListFavoritePokemonsImpl
import com.example.pokemon.lista.PokemonAdapter


class FavoritosFragment : Fragment(R.layout.fragment_favorite) {
    private lateinit var viewModel: FavoritePokemonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvfavorite)
        val progressfav = view.findViewById<ProgressBar>(R.id.progressfav)
        context?.let {
        viewModel = FavoritePokemonViewModel(
            ViewListFavoritePokemonsImpl(
                FavoriteRepositoryImpl(it),
                PokemonRepositoryImpl(it.cacheDir)
            )
        )
        }
        val adapter = PokemonAdapter( null,null,null,null)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter

        viewModel.pokemons.observe(viewLifecycleOwner, Observer {

           adapter.update(it)

            progressfav.visibility = View.GONE

        })

            viewModel.loadPokemons()


        }
        }





