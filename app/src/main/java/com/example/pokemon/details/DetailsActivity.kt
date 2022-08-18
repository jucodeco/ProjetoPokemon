package com.example.pokemon.details

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.api.PokemonRepositoryImpl
import com.example.pokemon.api.types.TypeRepositoryImpl
import com.example.pokemon.databinding.FragmentPokemonDetailsBinding
import com.example.pokemon.details.fragment.AboutFragment
import com.example.pokemon.details.fragment.BaseStatusFragment
import com.google.android.material.tabs.TabLayoutMediator


class DetailsActivity() : AppCompatActivity() {
    private lateinit var viewModel: DetailsPokemonViewModel
    private lateinit var binding: FragmentPokemonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tableLayout = binding.tabLayout
        val viewPager = binding.viewPager
        val adapter = TabViewPagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tableLayout, viewPager) { tab, position ->
            tab.text = getString(adapter.tabs[position])
        }.attach()
        
        supportActionBar?.hide()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val number = intent.getIntExtra(EXTRA_POKEMON_NUMBER, 0)
        val pokemonName = findViewById<TextView>(R.id.pokemonName)
        val pokemonId = findViewById<TextView>(R.id.pokemonId)
        val pokemonImg = findViewById<ImageView>(R.id.pokemonImg)
        val rootView = findViewById<ConstraintLayout>(R.id.rootView)
        viewModel = DetailsPokemonViewModel(
            PokemonRepositoryImpl(cacheDir),
            TypeRepositoryImpl((cacheDir))

        )

        viewModel.pokemonDetails.observe(this, Observer {
            pokemonName.text = it.name
            pokemonId.text = it.number.toString()
            Glide.with(this).load(it.image).into(pokemonImg)
            rootView.setBackgroundResource(it.backgroudColor)
            adapter.addDetail(it)

        })

            viewModel.loadPokemonDetails(number )

    }

    companion object {
        const val EXTRA_POKEMON_NUMBER = "extra pokemon number"
    }

    class TabViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
        val tabs = arrayOf(R.string.stats, R.string.about)
        var fragments = mutableListOf<Fragment>()

        override fun getItemCount() = fragments.size

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        fun addDetail(it: PokemonDetails) {
            fragments.add(AboutFragment.newInstance(it))
            fragments.add(BaseStatusFragment.newInstance(it))
            notifyDataSetChanged()
        }
    }
}

























