package com.example.pokemon.details.fragment

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pokemon.R
import com.example.pokemon.details.PokemonDetails


class BaseStatusFragment : Fragment(R.layout.fragment_base_status) {

    private var detail: PokemonDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detail = arguments?.getSerializable("num") as? PokemonDetails

    }

    @SuppressLint("ObjectAnimatorBinding")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hpInclude = view.findViewById<LinearLayout>(R.id.hp)
        val hpName = hpInclude.findViewById<TextView>(R.id.statName)
        val hpStat = hpInclude.findViewById<TextView>(R.id.stat)
        val hpValue = hpInclude.findViewById<ProgressBar>(R.id.statValue)

        hpName.text = "hp"

        hpStat.text = (detail?.stats?.firstOrNull{
            it.first=="hp"
        }?.second ?: 0).toString()

        hpValue.progress = detail?.stats?.firstOrNull{
            it.first=="hp"
        }?.second ?: 0

        val attackInclude = view.findViewById<LinearLayout>(R.id.attack)
        val attackName = attackInclude.findViewById<TextView>(R.id.statName)
        val attackStat = attackInclude.findViewById<TextView>(R.id.stat)
        val attackValue = attackInclude.findViewById<ProgressBar>(R.id.statValue)

        attackName.text = "attack"

        attackStat.text = (detail?.stats?.firstOrNull{
            it.first=="attack"
        }?.second ?: 0).toString()

        attackValue.progress = detail?.stats?.firstOrNull{
            it.first=="attack"
        }?.second?:0

        val defenseInclude = view.findViewById<LinearLayout>(R.id.defense)
        val defenseName = defenseInclude.findViewById<TextView>(R.id.statName)
        val defenseStat = defenseInclude.findViewById<TextView>(R.id.stat)
        val defenseValue = defenseInclude.findViewById<ProgressBar>(R.id.statValue)

        defenseName.text = "defense"

        defenseStat.text = (detail?.stats?.firstOrNull{
            it.first=="defense"
        }?.second ?: 0).toString()

        defenseValue.progress = detail?.stats?.firstOrNull{
            it.first=="defense"
        }?.second?:0

        val spArkInclude = view.findViewById<LinearLayout>(R.id.spArk)
        val spArkName = spArkInclude.findViewById<TextView>(R.id.statName)
        val spArkStat = spArkInclude.findViewById<TextView>(R.id.stat)
        val spArkValue = spArkInclude.findViewById<ProgressBar>(R.id.statValue)

        spArkName.text = "special-attack"

        spArkStat.text = (detail?.stats?.firstOrNull{
            it.first=="special-attack"
        }?.second ?: 0).toString()

        spArkValue.progress = detail?.stats?.firstOrNull{
            it.first == "special-attack"
        }?.second ?: 0

        val spDefInclude = view.findViewById<LinearLayout>(R.id.spDef)
        val spDefName = spDefInclude.findViewById<TextView>(R.id.statName)
        val spDefStat = spDefInclude.findViewById<TextView>(R.id.stat)
        val spDefValue = spDefInclude.findViewById<ProgressBar>(R.id.statValue)

        spDefName.text = "special-defense"

        spDefStat.text = (detail?.stats?.firstOrNull{
            it.first == "special-defense"
        }?.second ?: 0).toString()

        spDefValue.progress = detail?.stats?.firstOrNull{
            it.first == "special-defense"
        }?.second ?: 0

        val speedInclude = view.findViewById<LinearLayout>(R.id.speed)
        val speedName = speedInclude.findViewById<TextView>(R.id.statName)
        val speedStat = speedInclude.findViewById<TextView>(R.id.stat)
        val speedValue = speedInclude.findViewById<ProgressBar>(R.id.statValue)

        speedName.text = "speed"
        speedStat.text = (detail?.stats?.firstOrNull{
            it.first == "speed"
        }?.second ?:0).toString()

        speedValue.progress = detail?.stats?.firstOrNull{
            it.first == "speed"
        }?.second ?:0







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




