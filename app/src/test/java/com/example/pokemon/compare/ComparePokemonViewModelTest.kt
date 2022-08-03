package com.example.pokemon.compare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.api.PokemonRepository
import com.example.pokemon.api.model.DamageRelations
import com.example.pokemon.api.model.PokemonApiResult
import com.example.pokemon.api.model.PokemonTypeSlot
import com.example.pokemon.api.model.TypeResult
import com.example.pokemon.api.types.TypeRepository
import com.example.pokemon.domain.PokemonType
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule



class ComparePokemonViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val observer = mock<Observer<PokemonCompare>>()
    private val pokemonRepository = mock<PokemonRepository>()
    private val typeRepository = mock<TypeRepository>()
    private val comparePokemonViewModel = ComparePokemonViewModel(pokemonRepository,typeRepository)

    @Before
    fun setup() {
        comparePokemonViewModel.pokemonCompare.observeForever(observer)

    }

    @Test
    fun `given my pokemon repository show on the screen the image of the two pokemons`() {
        whenever(pokemonRepository.getPokemon(1)).doReturn(
            PokemonApiResult(
                1,
                "bulbasaur",
                listOf(PokemonTypeSlot(1, PokemonType("grass"))),
                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("urlleft"))),
                listOf(PokemonApiResult.PokemonStats(45, 49, PokemonApiResult.PokemonStatName("bulbasaur"))),
                7, 69

            )

        )

        whenever(pokemonRepository.getPokemon(2)).doReturn(
            PokemonApiResult(
                2,
                "ivysaur",
                listOf(PokemonTypeSlot(2, PokemonType("poison"))),

                PokemonApiResult.Sprites(PokemonApiResult.OtherSprites(PokemonApiResult.OfficialArtwork("urlright"))),
                listOf(PokemonApiResult.PokemonStats(60, 62, PokemonApiResult.PokemonStatName("ivysaur"))),
                10, 130
            )

        )

        whenever(typeRepository.typePokemon("grass")).doReturn(
            TypeResult(
                DamageRelations(
                    listOf(PokemonType("water")),
                    listOf(PokemonType("fire")),
                    listOf(PokemonType("poison")),
                    listOf(PokemonType("rock"))
                )
            )
        )

        whenever(typeRepository.typePokemon("poison")).doReturn(
            TypeResult(
                DamageRelations(
                    listOf(PokemonType("bug")),
                    listOf(PokemonType("grass")),
                    listOf(PokemonType("fire")),
                    listOf(PokemonType("rock"))
                )
            )
        )
        comparePokemonViewModel.comparePokemon(1, 2)
        verify(observer).onChanged(
            PokemonCompare(
                "urlleft",
                "urlright",
                "Bulbasaur",
                "Ivysaur",
                "poison",
                null,
                "grass",
                null,
                R.color.colorpoison,
                null,
                R.color.colorgrass,
                null,
                listOf(Pair("bulbasaur", 45)),
                listOf(Pair("ivysaur", 60)),
                listOf(PokemonCompareType("poison", R.color.colorpoison)),
                emptyList(),
                emptyList(),
                emptyList(),
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                 R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_baseline_compare_arrows_24,
                R.drawable.ic_hp_red,
                R.drawable.ic_hp_red,
                R.drawable.ic_attack,
                R.drawable.ic_attack,
                R.drawable.ic_defense,
                R.drawable.ic_defense,
                R.drawable.ic_spattack,
                R.drawable.ic_spattack,
                R.drawable.ic_spdef,
                R.drawable.ic_spdef,
                R.drawable.ic_speed,
                R.drawable.ic_speed











            )


        )
    }




}



