package com.example.pokemon.lista

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemon.R


@Composable
fun PokemonListScreen(
    list: List<PokemonItem>,
    onPokemonClick: ((PokemonItem) -> Unit)?,
    viewModel: PokemonViewModel,
    onComparePokemon: (Int, Int) -> Unit,
    onAddFavoriteListener: OnClickAddFavorite,
    onClickRemoveFavorite: OnClickRemoveFavorite,


    ) {

    var showCheckBox = remember {
        mutableStateOf(false)
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showCheckBox.value = !showCheckBox.value }, backgroundColor = colorResource(id = R.color.white))
            {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_compare_arrows_24), contentDescription = "Compare Pokemon")

            }
        },
        modifier = Modifier.padding(bottom = 56.dp)

    ) {


        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.pokemonf),
                    contentDescription = "Pokemon",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                )
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                }
                PokemonList(onPokemonClick, pokemons = list, onComparePokemon, showCheckBox.value, onAddFavoriteListener, onClickRemoveFavorite)
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .onFocusChanged {
                    it.isFocused
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

    }
}

@Composable
fun PokemonList(
    onPokemonClick: ((PokemonItem) -> Unit)?,
    pokemons: List<PokemonItem>,
    onComparePokemon: (Int, Int) -> Unit,
    showCheckBox: Boolean,
    addFavoriteListener: OnClickAddFavorite?,
    removeFavoriteListener: OnClickRemoveFavorite?,
) {
    val checkboxList = remember {
        mutableStateListOf<Int>()
    }
    Spacer(modifier = Modifier.height(20.dp))
    LazyVerticalGrid(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(2),


        ) {

        itemsIndexed(pokemons) { index, entry ->
            PokedexEntry(
                entry = entry,
                onPokemonClick,
                { id ->
                    checkboxList.add(id)
                    if (checkboxList.size == 2) {
                        onComparePokemon.invoke(checkboxList.get(0), checkboxList.get(1))
                        checkboxList.clear()

                    }

                },
                {
                    checkboxList.remove(it)
                },
                showCheckBox,
                checkboxList.contains(entry.id),
                addFavoriteListener,
                removeFavoriteListener


            )
        }
    }
}


@Composable
fun PokedexEntry(
    entry: PokemonItem,
    onPokemonClick: ((PokemonItem) -> Unit)?,
    onSelectedPokemon: (Int) -> Unit,
    onUnselectedPokemon: (Int) -> Unit,
    showCheckBox: Boolean,
    isChecked: Boolean,
    addFavoriteListener: OnClickAddFavorite?,
    removeFavoriteListener: OnClickRemoveFavorite?,
) {

    val checkedState = remember { mutableStateOf(false) }

    checkedState.value = isChecked
    Box(
        contentAlignment = Center,
        modifier = Modifier
            .shadow(5.dp, RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp))
            .aspectRatio(1f)
            .background(
                colorResource(id = entry.type1color)
            )
            .clickable {
                onPokemonClick?.invoke(entry)
            }
    ) {
        Column {
            Text(
                text = entry.name,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Row() {
                Image(painter = painterUrl(
                        entry.image,
                        painterResource(id = R.drawable.logopokebola)
                    ),
                    contentDescription = entry.name,
                    modifier = Modifier
                        .size(120.dp)

                )


            }
            Row() {

                IconButton(onClick = {
                    if (entry.isfav)
                        removeFavoriteListener?.onClickRemoveFavorite(entry)
                    else
                        addFavoriteListener?.onAddFavorite(entry)
                })
                {
                    Icon(
                        painter = painterResource(
                            id = (if (entry.isfav) R.drawable.ic_fav else R.drawable.ic_favorite)
                        ), contentDescription = "Favorite"
                    )
                }
                if (showCheckBox) {
                    Checkbox(

                        checked = checkedState.value,
                        onCheckedChange = {
                            checkedState.value = it
                            if (it) {
                                onSelectedPokemon.invoke(entry.id)

                            } else onUnselectedPokemon.invoke(entry.id)


                        })

                }


            }


        }
    }

}













