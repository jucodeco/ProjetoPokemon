package com.example.pokemon.view

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.viewmodel.PokemonItem
import com.example.pokemon.viewmodel.PokemonViewModel
import com.example.pokemon.viewmodel.PokemonViewModelFactory


class MainActivity : AppCompatActivity() {



    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.rvPokemons)
    }
    private val progress by lazy {
        findViewById<ProgressBar>(R.id.progress)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
            progress.visibility = View.GONE
        })
        viewModel.loadPokemons()
    }

    private fun loadRecyclerView(pokemons: List<PokemonItem?>) {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = PokemonAdapter (pokemons)
    }




    }


