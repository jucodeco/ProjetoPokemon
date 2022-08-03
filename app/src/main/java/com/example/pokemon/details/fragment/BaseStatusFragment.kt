package com.example.pokemon.details.fragment


import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.details.DetailAdapter
import com.example.pokemon.details.PokemonDetails
import com.example.pokemon.details.PokemonDetailsType


class BaseStatusFragment : Fragment(R.layout.fragment_base_status) {

    private var detail: PokemonDetails? = null

    private var detailResistance: PokemonDetailsType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detail = arguments?.getSerializable("num") as? PokemonDetails

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hpInclude = view.findViewById<LinearLayout>(R.id.hp)
        val hpName = hpInclude.findViewById<ImageView>(R.id.statName)
        val hpStat = hpInclude.findViewById<TextView>(R.id.stat)
        val hpValue = hpInclude.findViewById<ProgressBar>(R.id.statValue)

        hpName.setImageResource(detail?.hp?:0)

        hpStat.text = (detail?.stats?.firstOrNull {
            it.first == "hp"
        }?.second ?: 0).toString()

        val hpAnimator = ObjectAnimator.ofInt(hpValue,"progress",detail?.stats?.firstOrNull {
            it.first == "hp"
        }?.second ?: 0 )
        hpAnimator.setStartDelay(1000L)
        hpAnimator.setDuration(500)
        hpAnimator.setInterpolator(DecelerateInterpolator())
        hpAnimator.start()

        val attackInclude = view.findViewById<LinearLayout>(R.id.attack)
        val attackName = attackInclude.findViewById<ImageView>(R.id.statName)
        val attackStat = attackInclude.findViewById<TextView>(R.id.stat)
        val attackValue = attackInclude.findViewById<ProgressBar>(R.id.statValue)

        attackName.setImageResource(detail?.attack?:0)

        attackStat.text = (detail?.stats?.firstOrNull {
            it.first == "attack"
        }?.second ?: 0).toString()


        val attackAnimator = ObjectAnimator.ofInt(attackValue,"progress", detail?.stats?.firstOrNull {
            it.first == "attack"
        }?.second ?: 0)
        attackAnimator.setStartDelay(1000L)
        attackAnimator.setDuration(500)
        attackAnimator.setInterpolator(DecelerateInterpolator())
        attackAnimator.start()

        val defenseInclude = view.findViewById<LinearLayout>(R.id.defense)
        val defenseName = defenseInclude.findViewById<ImageView>(R.id.statName)
        val defenseStat = defenseInclude.findViewById<TextView>(R.id.stat)
        val defenseValue = defenseInclude.findViewById<ProgressBar>(R.id.statValue)

        defenseName.setImageResource(detail?.defense?:0)

        defenseStat.text = (detail?.stats?.firstOrNull {
            it.first == "defense"
        }?.second ?: 0).toString()

        val defenseAnimator = ObjectAnimator.ofInt(defenseValue, "progress", detail?.stats?.firstOrNull {
            it.first == "defense"
        }?.second ?: 0)
        defenseAnimator.setStartDelay(1000L)
        defenseAnimator.setDuration(500)
        defenseAnimator.setInterpolator(DecelerateInterpolator())
        defenseAnimator.start()

        val spArkInclude = view.findViewById<LinearLayout>(R.id.spArk)
        val spArkName = spArkInclude.findViewById<ImageView>(R.id.statName)
        val spArkStat = spArkInclude.findViewById<TextView>(R.id.stat)
        val spArkValue = spArkInclude.findViewById<ProgressBar>(R.id.statValue)

        spArkName.setImageResource(detail?.spArk?:0)

        spArkStat.text = (detail?.stats?.firstOrNull {
            it.first == "special-attack"
        }?.second ?: 0).toString()

        val spArkAnimator = ObjectAnimator.ofInt(spArkValue, "progress",detail?.stats?.firstOrNull {
            it.first == "special-attack"
        }?.second ?: 0 )
        spArkAnimator.setStartDelay(1000L)
        spArkAnimator.setDuration(500)
        spArkAnimator.setInterpolator(DecelerateInterpolator())
        spArkAnimator.start()

        val spDefInclude = view.findViewById<LinearLayout>(R.id.spDef)
        val spDefName = spDefInclude.findViewById<ImageView>(R.id.statName)
        val spDefStat = spDefInclude.findViewById<TextView>(R.id.stat)
        val spDefValue = spDefInclude.findViewById<ProgressBar>(R.id.statValue)

        spDefName.setImageResource(detail?.spDef?:0)

        spDefStat.text = (detail?.stats?.firstOrNull {
            it.first == "special-defense"
        }?.second ?: 0).toString()

        val defAnimator = ObjectAnimator.ofInt(spDefValue, "progress", detail?.stats?.firstOrNull {
            it.first == "special-defense"
        }?.second ?: 0)
        defAnimator.setStartDelay(1000L)
        defAnimator.setDuration(500)
        defAnimator.setInterpolator(DecelerateInterpolator())
        defAnimator.start()

        val speedInclude = view.findViewById<LinearLayout>(R.id.speed)
        val speedName = speedInclude.findViewById<ImageView>(R.id.statName)
        val speedStat = speedInclude.findViewById<TextView>(R.id.stat)
        val speedValue = speedInclude.findViewById<ProgressBar>(R.id.statValue)

        speedName.setImageResource(detail?.speed?:0)
        speedStat.text = (detail?.stats?.firstOrNull {
            it.first == "speed"
        }?.second ?: 0).toString()

        val speedAnimator = ObjectAnimator.ofInt(speedValue, "progress", detail?.stats?.firstOrNull {
            it.first == "speed"
        }?.second ?: 0)
        speedAnimator.setStartDelay(1000L)
        speedAnimator.setDuration(500)
        speedAnimator.setInterpolator(DecelerateInterpolator())
        speedAnimator.start()


        val recyclerView = view.findViewById<RecyclerView>(R.id.weaknessRecyclerView)
        val recyclerViewResistance = view.findViewById<RecyclerView>(R.id.resistantRecycler)
        val adapter = DetailAdapter(detail?.weakness)
        val adapterRe = DetailAdapter(detail?.resistance)
        recyclerView.adapter = adapter
        recyclerViewResistance.adapter = adapterRe
    }

    companion object {
        fun newInstance(detail: PokemonDetails): BaseStatusFragment {
            val f = BaseStatusFragment()
            val args = Bundle()
            args.putSerializable("num", detail)
            f.arguments = args

            return f
        }

    }
}




