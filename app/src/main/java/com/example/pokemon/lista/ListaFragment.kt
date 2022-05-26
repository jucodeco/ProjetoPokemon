package com.example.pokemon.lista

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R

class ListaFragment : Fragment(R.layout.fragment_lista) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvPokemons)

        val progress = view.findViewById<ProgressBar>(R.id.progress)

        val viewModel = ViewModelProvider(this, PokemonViewModelFactory())
                .get(PokemonViewModel::class.java)

        viewModel.pokemons.observe(viewLifecycleOwner, Observer {

            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerView.adapter = PokemonAdapter (it)
            progress.visibility = View.GONE
        })
        viewModel.loadPokemons()

    }
    }


