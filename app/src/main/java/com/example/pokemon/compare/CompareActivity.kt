package com.example.pokemon.compare


import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentCompareBinding
import org.w3c.dom.Text


class CompareActivity : AppCompatActivity(R.layout.fragment_compare) {
    private lateinit var binding: FragmentCompareBinding
    private lateinit var viewModel: ComparePokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCompareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val numLeft = intent.getIntExtra(COMPARE_POKEMON_LEFT, 0)
        val numRight = intent.getIntExtra(COMPARE_POKEMON_RIGHT, 1)
        viewModel = ViewModelProvider(this, PokemonCompareViewModelFactory(this))
            .get(ComparePokemonViewModel::class.java)

        val leftInclude = findViewById<CardView>(R.id.firstPokemon)
        val leftImage = leftInclude.findViewById<ImageView>(R.id.pokeball)

        val rightInclude = findViewById<CardView>(R.id.secondPokemon)
        val rightImage = rightInclude.findViewById<ImageView>(R.id.pokeball)

        val nameLeft = leftInclude.findViewById<TextView>(R.id.namePokemon)
        val nameRight = rightInclude.findViewById<TextView>(R.id.namePokemon)

        viewModel.pokemonCompare.observe(this, Observer {

            Glide.with(this).load(it.imageLeft).into(leftImage)
            Glide.with(this).load(it.imageRight).into(rightImage)

            nameLeft.text = it.nameLeft
            nameRight.text = it.nameRight

        })


        Thread(Runnable {
            viewModel.comparePokemon(numLeft,numRight)
        })
            .start()

    }

    companion object {
        const val COMPARE_POKEMON_LEFT = "COMPARE_POKEMON"
        const val COMPARE_POKEMON_RIGHT = "COMPARE_POKEMON2"
    }


}