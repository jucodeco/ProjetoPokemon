package com.example.pokemon.details.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pokemon.R
import com.example.pokemon.details.PokemonDetails


class AboutFragment : Fragment(R.layout.fragment_about) {

    private var detail: PokemonDetails? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detail = arguments?.getSerializable("num") as? PokemonDetails
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonHeight = view.findViewById<TextView>(R.id.pokemonHeight)
        pokemonHeight.text = detail?.height?.toString()
        val pokemonWeight = view.findViewById<TextView>(R.id.pokemonWeight)
        pokemonWeight.text = detail?.weight?.toString()
    }

    companion object {
        fun newInstance(detail: PokemonDetails): AboutFragment {
            val f = AboutFragment()
            val args = Bundle()
            args.putSerializable("num", detail)
            f.arguments = args
            return f
        }
    }

}



