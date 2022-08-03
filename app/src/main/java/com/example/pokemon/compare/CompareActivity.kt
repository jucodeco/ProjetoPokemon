package com.example.pokemon.compare


import android.animation.ObjectAnimator
import android.content.ContentProvider
import android.content.Context
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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentCompareBinding
import com.example.pokemon.details.DetailAdapter
import com.example.pokemon.lista.PokemonViewModel
import com.example.pokemon.lista.PokemonViewModelFactory


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

        val leftCard = findViewById<CardView>(R.id.firstPokemon)
        val leftImage = leftCard.findViewById<ImageView>(R.id.pokeball)

        val rightCard = findViewById<CardView>(R.id.secondPokemon)
        val rightImage = rightCard.findViewById<ImageView>(R.id.pokeball)

        val namePokemonLeft = leftCard.findViewById<TextView>(R.id.namePokemon)
        val namePokemonRight = rightCard.findViewById<TextView>(R.id.namePokemon)

        val nameTypeLeft1 = leftCard.findViewById<TextView>(R.id.pokemonType1)
        val nameTypeLeft2 = leftCard.findViewById<TextView>(R.id.pokemonType2)
        val nameTypeRight1 = rightCard.findViewById<TextView>(R.id.pokemonType1)
        val nameTypeRight2 = rightCard.findViewById<TextView>(R.id.pokemonType2)


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

            val hpInclude = findViewById<ConstraintLayout>(R.id.hpPokemon)
            val hpName = hpInclude.findViewById<ImageView>(R.id.statName)
            val hpStat = hpInclude.findViewById<TextView>(R.id.stat)
            val hpValue = hpInclude.findViewById<ProgressBar>(R.id.statValue)
            val imageHpLeft = hpInclude.findViewById<ImageView>(R.id.imageprogressbar)

            hpName.setImageResource(it.hpLeft)

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

            imageHpLeft.setImageResource(it.hpIconLeft)


            val attackLeftInclude = findViewById<ConstraintLayout>(R.id.attackPokemon)
            val attackLeftName = attackLeftInclude.findViewById<ImageView>(R.id.statName)
            val attackLeftStat = attackLeftInclude.findViewById<TextView>(R.id.stat)
            val attackLeftValue = attackLeftInclude.findViewById<ProgressBar>(R.id.statValue)
            val imageAttackLeft = attackLeftInclude.findViewById<ImageView>(R.id.imageprogressbar)

            attackLeftName.setImageResource(it.attackLeft)

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

            imageAttackLeft.setImageResource(it.attackIconLeft)

            val defenseLeftInclude = findViewById<ConstraintLayout>(R.id.defensePokemon)
            val defenseLeftName = defenseLeftInclude.findViewById<ImageView>(R.id.statName)
            val defenseLeftStat = defenseLeftInclude.findViewById<TextView>(R.id.stat)
            val defenseLeftValue = defenseLeftInclude.findViewById<ProgressBar>(R.id.statValue)
            val imageDefenseLeft = defenseLeftInclude.findViewById<ImageView>(R.id.imageprogressbar)

            defenseLeftName.setImageResource(it.defenseLeft)

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

            imageDefenseLeft.setImageResource(it.defenseIconLeft)

            val spArkLeft = leftCard.findViewById<ConstraintLayout>(R.id.spArkPokemon)
            val spArkLeftName = spArkLeft.findViewById<ImageView>(R.id.statName)
            val spArkLeftStat = spArkLeft.findViewById<TextView>(R.id.stat)
            val spArkLeftValue = spArkLeft.findViewById<ProgressBar>(R.id.statValue)
            val imageSpArkLeft = spArkLeft.findViewById<ImageView>(R.id.imageprogressbar)

            spArkLeftName.setImageResource(it.spArkLeft)

            spArkLeftStat.text = (it.statsLeft.firstOrNull {
                it.first == "special-attack"
            }?.second ?: 0).toString()

            val spArkLeftAnimator = ObjectAnimator.ofInt(spArkLeftValue, "progress", it?.statsLeft?.firstOrNull {
                it.first == "special-attack"
            }?.second ?: 0)

            spArkLeftAnimator.setStartDelay(1000L)
            spArkLeftAnimator.setDuration(500)
            spArkLeftAnimator.setInterpolator(DecelerateInterpolator())
            spArkLeftAnimator.start()

            imageSpArkLeft.setImageResource(it.spArkIconLeft)

            val spDefLeft = leftCard.findViewById<ConstraintLayout>(R.id.spDefPokemon)
            val spDefLeftName = spDefLeft.findViewById<ImageView>(R.id.statName)
            val spDefLeftStat = spDefLeft.findViewById<TextView>(R.id.stat)
            val spDefLeftValue = spDefLeft.findViewById<ProgressBar>(R.id.statValue)
            val imageSpDefLeft = spDefLeft.findViewById<ImageView>(R.id.imageprogressbar)

            spDefLeftName.setImageResource(it.spDefLeft)

            spDefLeftStat.text = (it.statsLeft.firstOrNull {
                it.first == "special-defense"
            }?.second ?: 0).toString()

            val defLeftAnimator = ObjectAnimator.ofInt(spDefLeftValue, "progress", it.statsLeft.firstOrNull {
                it.first == "special-defense"
            }?.second ?: 0)
            defLeftAnimator.setStartDelay(1000L)
            defLeftAnimator.setDuration(500)
            defLeftAnimator.setInterpolator(DecelerateInterpolator())
            defLeftAnimator.start()

            imageSpDefLeft.setImageResource(it.spDefIconLeft)

            val speedLeft = leftCard.findViewById<ConstraintLayout>(R.id.speedPokemon)
            val speedLeftName = speedLeft.findViewById<ImageView>(R.id.statName)
            val speedLeftStat = speedLeft.findViewById<TextView>(R.id.stat)
            val speedLeftValue = speedLeft.findViewById<ProgressBar>(R.id.statValue)
            val imageSpeedLeft = speedLeft.findViewById<ImageView>(R.id.imageprogressbar)

            speedLeftName.setImageResource(it.speedLeft)

            speedLeftStat.text = (it.statsLeft.firstOrNull {
                it.first == "speed"
            }?.second ?: 0).toString()

            val speedLeftAnimator = ObjectAnimator.ofInt(speedLeftValue, "progress", it.statsLeft.firstOrNull {
                it.first == "speed"
            }?.second ?: 0)
            speedLeftAnimator.setStartDelay(1000L)
            speedLeftAnimator.setDuration(500)
            speedLeftAnimator.setInterpolator(DecelerateInterpolator())
            speedLeftAnimator.start()

            imageSpeedLeft.setImageResource(it.speedIconLeft)


            val hpRight = rightCard.findViewById<ConstraintLayout>(R.id.hpPokemon)
            val hpNameRight = hpRight.findViewById<ImageView>(R.id.statName)
            val hpStatRight = hpRight.findViewById<TextView>(R.id.stat)
            val hpValueRight = hpRight.findViewById<ProgressBar>(R.id.statValue)
            val imageHpRight = hpRight.findViewById<ImageView>(R.id.imageprogressbar)

            hpNameRight.setImageResource(it.hpRight)

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

            imageHpRight.setImageResource(it.hpIconRight)


            val attackRight = rightCard.findViewById<ConstraintLayout>(R.id.attackPokemon)
            val attackRightName = attackRight.findViewById<ImageView>(R.id.statName)
            val attackRightStat = attackRight.findViewById<TextView>(R.id.stat)
            val attackRightValue = attackRight.findViewById<ProgressBar>(R.id.statValue)
            val imageAttackRight = attackRight.findViewById<ImageView>(R.id.imageprogressbar)


            attackRightName.setImageResource(it.attackRight)

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

            imageAttackRight.setImageResource(it.attackIconRight)

            val defenseRight = rightCard.findViewById<ConstraintLayout>(R.id.defensePokemon)
            val defenseRightName = defenseRight.findViewById<ImageView>(R.id.statName)
            val defenseRightStat = defenseRight.findViewById<TextView>(R.id.stat)
            val defenseRightValue = defenseRight.findViewById<ProgressBar>(R.id.statValue)
            val imageDefenseRight = defenseRight.findViewById<ImageView>(R.id.imageprogressbar)

            defenseRightName.setImageResource(it.defenseRight)

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

            imageDefenseRight.setImageResource(it.attackIconRight)

            val spArkRight = rightCard.findViewById<ConstraintLayout>(R.id.spArkPokemon)
            val spArkRightName = spArkRight.findViewById<ImageView>(R.id.statName)
            val spArkRightStat = spArkRight.findViewById<TextView>(R.id.stat)
            val spArkRightValue = spArkRight.findViewById<ProgressBar>(R.id.statValue)
            val imageSpArkRight = spArkRight.findViewById<ImageView>(R.id.imageprogressbar)

            spArkRightName.setImageResource(it.spArkRight)

            spArkRightStat.text = (it.statsRight.firstOrNull {
                it.first == "special-attack"
            }?.second ?: 0).toString()

            val spArkRightAnimator = ObjectAnimator.ofInt(spArkRightValue, "progress", it?.statsRight?.firstOrNull {
                it.first == "special-attack"
            }?.second ?: 0)

            spArkRightAnimator.setStartDelay(1000L)
            spArkRightAnimator.setDuration(500)
            spArkRightAnimator.setInterpolator(DecelerateInterpolator())
            spArkRightAnimator.start()

            imageSpArkRight.setImageResource(it.spArkIconRight)

            val spDefRight = rightCard.findViewById<ConstraintLayout>(R.id.spDefPokemon)
            val spDefRightName = spDefRight.findViewById<ImageView>(R.id.statName)
            val spDefRightStat = spDefRight.findViewById<TextView>(R.id.stat)
            val spDefRightValue = spDefRight.findViewById<ProgressBar>(R.id.statValue)
            val imageSpDefRight = spDefRight.findViewById<ImageView>(R.id.imageprogressbar)

            spDefRightName.setImageResource(it.spDefRight)

            spDefRightStat.text = (it.statsRight.firstOrNull {
                it.first == "special-defense"
            }?.second ?: 0).toString()

            val defRightAnimator = ObjectAnimator.ofInt(spDefRightValue, "progress", it.statsRight.firstOrNull {
                it.first == "special-defense"
            }?.second ?: 0)
            defRightAnimator.setStartDelay(1000L)
            defRightAnimator.setDuration(500)
            defRightAnimator.setInterpolator(DecelerateInterpolator())
            defRightAnimator.start()

            imageSpDefRight.setImageResource(it.spDefIconRight)


            val speedRight = rightCard.findViewById<ConstraintLayout>(R.id.speedPokemon)
            val speedRightName = speedRight.findViewById<ImageView>(R.id.statName)
            val speedRightStat = speedRight.findViewById<TextView>(R.id.stat)
            val speedRightValue = speedRight.findViewById<ProgressBar>(R.id.statValue)
            val imageSpeedRight = speedRight.findViewById<ImageView>(R.id.imageprogressbar)

            speedRightName.setImageResource(it.speedRight)

            speedRightStat.text = (it.statsRight.firstOrNull {
                it.first == "speed"
            }?.second ?: 0).toString()

            val speedRightAnimator = ObjectAnimator.ofInt(speedRightValue, "progress", it.statsRight.firstOrNull {
                it.first == "speed"
            }?.second ?: 0)
            speedRightAnimator.setStartDelay(1000L)
            speedRightAnimator.setDuration(500)
            speedRightAnimator.setInterpolator(DecelerateInterpolator())
            speedRightAnimator.start()

            imageSpeedRight.setImageResource(it.speedIconRight)


            val recyclerViewLeftResistant = leftCard.findViewById<RecyclerView>(R.id.resistantRecyclerCompare)
            val recyclerViewRightResistant = rightCard.findViewById<RecyclerView>(R.id.resistantRecyclerCompare)

            recyclerViewLeftResistant.layoutManager = LinearLayoutManager(this)
            recyclerViewRightResistant.layoutManager = LinearLayoutManager(this)

            val adapterLeftResistance = CompareAdapter(it.resistanceLeft)
            val adapterRightResistance = CompareAdapter(it.resistanceRight)

            recyclerViewLeftResistant.adapter = adapterLeftResistance
            recyclerViewRightResistant.adapter = adapterRightResistance

            val recyclerViewLeftWeak = leftCard.findViewById<RecyclerView>(R.id.weakRecyclerCompare)
            val recyclerViewRightWeak = rightCard.findViewById<RecyclerView>(R.id.weakRecyclerCompare)

            recyclerViewLeftWeak.layoutManager = LinearLayoutManager(this)
            recyclerViewRightWeak.layoutManager = LinearLayoutManager(this)

            val adapterLeftWeak = CompareAdapter(it.weaknessLeft)
            val adapterRightWeak = CompareAdapter(it.weaknessRight)

            recyclerViewLeftWeak.adapter = adapterLeftWeak
            recyclerViewRightWeak.adapter = adapterRightWeak


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