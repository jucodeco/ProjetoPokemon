package com.example.pokemon.compare


import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
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
        binding = com.example.pokemon.databinding.FragmentCompareBinding.inflate(layoutInflater)
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

            val hpInclude = findViewById<LinearLayout>(R.id.hpPokemon)
            val hpName = hpInclude.findViewById<TextView>(R.id.statName)
            val hpStat = hpInclude.findViewById<TextView>(R.id.stat)
            val hpValue = hpInclude.findViewById<ProgressBar>(R.id.statValue)

            hpName.text = "hp"

            hpStat.text = (it?.statsLeft?.firstOrNull {
                it.first == "hp"
            }?.second ?: 0).toString()

            val hpLeftAnimator = ObjectAnimator.ofInt(hpValue, "progress", it?.statsLeft?.firstOrNull {
                it.first == "hp"
            }?.second ?: 0)
            hpLeftAnimator.setStartDelay(1000L)
            hpLeftAnimator.setDuration(500)
            hpLeftAnimator.setInterpolator(DecelerateInterpolator())
            hpLeftAnimator.start()

            val attackLeftInclude = findViewById<LinearLayout>(R.id.attackPokemon)
            val attackLeftName = attackLeftInclude.findViewById<TextView>(R.id.statName)
            val attackLeftStat = attackLeftInclude.findViewById<TextView>(R.id.stat)
            val attackLeftValue = attackLeftInclude.findViewById<ProgressBar>(R.id.statValue)

            attackLeftName.text = "attack"

            attackLeftStat.text = (it.statsLeft.firstOrNull {
                it.first == "attack"
            }?.second ?: 0).toString()
            val attackLeftAnimator = ObjectAnimator.ofInt(attackLeftValue, "progress", it?.statsLeft?.firstOrNull {
                it.first == "attack"
            }?.second ?: 0)
            attackLeftAnimator.setStartDelay(1000L)
            attackLeftAnimator.setDuration(500)
            attackLeftAnimator.setInterpolator(DecelerateInterpolator())
            attackLeftAnimator.start()

            val defenseLeftInclude = findViewById<LinearLayout>(R.id.defensePokemon)
            val defenseLeftName = defenseLeftInclude.findViewById<TextView>(R.id.statName)
            val defenseLeftStat = defenseLeftInclude.findViewById<TextView>(R.id.stat)
            val defenseLeftValue = defenseLeftInclude.findViewById<ProgressBar>(R.id.statValue)

            defenseLeftName.text = "defense"

            defenseLeftStat.text = (it.statsLeft.firstOrNull {
                it.first == "defense"
            }?.second ?: 0).toString()

            val defenseLeftAnimator = ObjectAnimator.ofInt(defenseLeftValue, "progress", it?.statsLeft?.firstOrNull {
                it.first == "defense"
            }?.second ?: 0)
            defenseLeftAnimator.setStartDelay(1000L)
            defenseLeftAnimator.setDuration(500)
            defenseLeftAnimator.setInterpolator(DecelerateInterpolator())
            defenseLeftAnimator.start()

            val hpIncludeRight = findViewById<CardView>(R.id.secondPokemon)
            val hpRight = hpIncludeRight.findViewById<LinearLayout>(R.id.hpPokemon)
            val hpNameRight = hpRight.findViewById<TextView>(R.id.statName)
            val hpStatRight = hpRight.findViewById<TextView>(R.id.stat)
            val hpValueRight = hpRight.findViewById<ProgressBar>(R.id.statValue)

            hpNameRight.text = "hp"

            hpStatRight.text = (it?.statsRight?.firstOrNull {
                it.first == "hp"
            }?.second ?: 0).toString()

            val hpRightAnimator = ObjectAnimator.ofInt(hpValueRight, "progress", it?.statsRight?.firstOrNull {
                it.first == "hp"
            }?.second ?: 0)
            hpRightAnimator.setStartDelay(1000L)
            hpRightAnimator.setDuration(500)
            hpRightAnimator.setInterpolator(DecelerateInterpolator())
            hpRightAnimator.start()


            val attackIncludeRight = findViewById<CardView>(R.id.secondPokemon)
            val attackRight = attackIncludeRight.findViewById<LinearLayout>(R.id.attackPokemon)
            val attackRightName = attackRight.findViewById<TextView>(R.id.statName)
            val attackRightStat = attackRight.findViewById<TextView>(R.id.stat)
            val attackRightValue = attackRight.findViewById<ProgressBar>(R.id.statValue)

            attackRightName.text = "attack"

            attackRightStat.text = (it.statsRight.firstOrNull {
                it.first == "attack"
            }?.second ?: 0).toString()

            val attackRightAnimator = ObjectAnimator.ofInt(attackRightValue, "progress", it?.statsRight?.firstOrNull {
                it.first == "attack"
            }?.second ?: 0)
            attackRightAnimator.setStartDelay(1000L)
            attackRightAnimator.setDuration(500)
            attackRightAnimator.setInterpolator(DecelerateInterpolator())
            attackRightAnimator.start()

            val defenseIncludeRight = findViewById<CardView>(R.id.secondPokemon)
            val defenseRight = defenseIncludeRight.findViewById<LinearLayout>(R.id.defensePokemon)
            val defenseRightName = defenseRight.findViewById<TextView>(R.id.statName)
            val defenseRightStat = defenseRight.findViewById<TextView>(R.id.stat)
            val defenseRightValue = defenseRight.findViewById<ProgressBar>(R.id.statValue)

            defenseRightName.text = "defense"

            defenseRightStat.text = (it.statsRight.firstOrNull {
                it.first == "defense"
            }?.second ?: 0).toString()

            val defenseRightAnimator = ObjectAnimator.ofInt(defenseRightValue, "progress", it?.statsRight?.firstOrNull {
                it.first == "defense"
            }?.second ?: 0)
            defenseRightAnimator.setStartDelay(1000L)
            defenseRightAnimator.setDuration(500)
            defenseRightAnimator.setInterpolator(DecelerateInterpolator())
            defenseRightAnimator.start()


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