package com.example.pokemon.compare


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentCompareBinding

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

        val namePokemonLeft = leftInclude.findViewById<TextView>(R.id.namePokemon)
        val namePokemonRight = rightInclude.findViewById<TextView>(R.id.namePokemon)

        val nameTypeLeft1 = leftInclude.findViewById<TextView>(R.id.pokemonType1)
        val nameTypeLeft2 = leftInclude.findViewById<TextView>(R.id.pokemonType2)
        val nameTypeRight1 = rightInclude.findViewById<TextView>(R.id.pokemonType1)
        val nameTypeRight2 = rightInclude.findViewById<TextView>(R.id.pokemonType2)

        viewModel.pokemonCompare.observe(this, Observer {

            Glide.with(this).load(it.imageLeft).into(leftImage)
            Glide.with(this).load(it.imageRight).into(rightImage)

            namePokemonLeft.text = it.nameLeft
            namePokemonRight.text = it.nameRight

            nameTypeLeft1.text = it.typeLeft1
            nameTypeLeft2.text = it.typeLeft2
            nameTypeRight1.text = it.typeRight1
            nameTypeRight2.text = it.typeRight2

            nameTypeLeft1.setBackgroundResource(it.typeColorLeft1)

            if (it.typeLeft2 != null && it.typeColorLeft2 != null) {
                nameTypeLeft2.visibility = View.VISIBLE
                nameTypeLeft2.text = it.typeLeft2
                nameTypeLeft2.setBackgroundResource(it.typeColorLeft2)
            } else {
                nameTypeLeft2.visibility = View.GONE
            }

            nameTypeRight1.setBackgroundResource(it.typeColorRight1)

            if (it.typeRight2 != null && it.typeColorRight2 != null) {
                nameTypeRight2.visibility = View.VISIBLE
                nameTypeRight2.text = it.typeRight2
                nameTypeRight2.setBackgroundResource(it.typeColorRight2)
            } else {
                nameTypeRight2.visibility = View.GONE
            }


        })


        Thread(Runnable {
            viewModel.comparePokemon(numLeft, numRight)
        })
            .start()

    }

    companion object {
        const val COMPARE_POKEMON_LEFT = "COMPARE_POKEMON"
        const val COMPARE_POKEMON_RIGHT = "COMPARE_POKEMON2"
    }


}