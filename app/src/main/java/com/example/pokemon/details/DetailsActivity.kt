package com.example.pokemon.details



import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.R



class DetailsActivity () : AppCompatActivity () {
    private lateinit var viewModel: DetailsPokemonViewModel

    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pokemon_details)
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val number = intent.getIntExtra(EXTRA_POKEMON_NUMBER, 0)
        val pokemonName = findViewById<TextView>(R.id.pokemonName)



        viewModel = ViewModelProvider(this, PokemonDetailsViewModelFactory(this))
            .get(DetailsPokemonViewModel::class.java)

        viewModel.pokemonDetails.observe(this, Observer {
        pokemonName.text = it.name



        })
        Thread(Runnable {
            viewModel.loadPokemonDetails(number)
        })
            .start()
    }
    companion object {
        const val EXTRA_POKEMON_NUMBER = "extra pokemon number"
    }


}









