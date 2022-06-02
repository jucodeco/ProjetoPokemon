package com.example.pokemon.favoritos

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.pokemon.R
import com.example.pokemon.lista.OnClickRemoveFavorite
import com.example.pokemon.lista.PokemonAdapter
import com.example.pokemon.lista.PokemonItem


class favoritosfragment : Fragment(R.layout.fragment_favoritos) {
    private lateinit var viewModel: FavoritePokemonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvfavorite)
        val progressfav = view.findViewById<ProgressBar>(R.id.progressfav)
        viewModel = ViewModelProvider(this, FavoritePokemonFactory(view.context))
            .get(FavoritePokemonViewModel::class.java)
        val adapter = PokemonAdapter( null,null)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {

           adapter.update(it)

            progressfav.visibility = View.GONE

        })
        viewModel.loadPokemons()



            }

        }





