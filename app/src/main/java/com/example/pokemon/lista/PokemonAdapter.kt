package com.example.pokemon.lista

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R

class PokemonAdapter(

    private val addFavoriteListener: OnClickAddFavorite?,
    private val removeFavoriteListener: OnClickRemoveFavorite?,
    private val onItemClick: ((PokemonItem) -> Unit)?,
    private val onComparePokemon: ((Int, Int) -> Unit)?


) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val items: MutableList<PokemonItem?> = mutableListOf()
    private var showcheckBox: Boolean = false
    private val checkboxList: MutableList<Int> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(
            item,
            addFavoriteListener,
            removeFavoriteListener,
            onItemClick,
            showcheckBox,
            {

                checkboxList.add(it)
                if (checkboxList.size == 2) {
                    onComparePokemon?.invoke(checkboxList.get(0), checkboxList.get(1))
                    checkboxList.clear()
                    notifyDataSetChanged()
                }

            },
            {

                checkboxList.remove(it)

            },
            checkboxList.contains(item?.id)
        )

    }

    fun update(it: List<PokemonItem?>) {
        items.clear()
        items.addAll(it)
        notifyDataSetChanged()
    }

    fun toggleCheckbox() {
        showcheckBox = !showcheckBox
        notifyDataSetChanged()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            item: PokemonItem?,
            addFavoriteListener: OnClickAddFavorite?,
            removeFavoriteListener: OnClickRemoveFavorite?,
            onItemClick: ((PokemonItem) -> Unit)?,
            showcheckBox: Boolean,
            OnCheckListener: ((Int) -> Unit)?,
            onRemoveCheckListener: ((Int) -> Unit)?,
            isCheckPokemon: Boolean
        ) =
            with(itemView) {
                val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
                val tvNumber = findViewById<TextView>(R.id.tvNumber)
                val tvName = findViewById<TextView>(R.id.tvName)
                val tvType1 = findViewById<TextView>(R.id.tvType1)
                val tvType2 = findViewById<TextView>(R.id.tvType2)
                val favorite = findViewById<ImageButton>(R.id.favorite)
                val checkbox = findViewById<CheckBox>(R.id.checkbox_list)

                item?.let {
                    itemView.setOnClickListener {
                        onItemClick?.invoke(item)
                    }
                    Glide.with(itemView.context).load(it.image).into(ivPokemon)

                    tvNumber.text = item.number
                    tvName.text = item.name
                    tvType1.text = item.type1
                    tvType1.setBackgroundResource(item.type1color)

                    if (item.type2 != null && item.type2color != null) {
                        tvType2.visibility = View.VISIBLE
                        tvType2.text = item.type2
                        tvType2.setBackgroundResource(item.type2color)
                    } else {
                        tvType2.visibility = View.GONE
                    }
                    favorite.setOnClickListener(View.OnClickListener {
                        if (item.isfav)
                            removeFavoriteListener?.onClickRemoveFavorite(item)
                        else
                            addFavoriteListener?.onAddFavorite(item)
                    })
                    favorite.setImageResource(if (item.isfav) R.drawable.ic_fav else R.drawable.ic_favorite)
                    checkbox.visibility = if (showcheckBox) View.VISIBLE else View.GONE
                    checkbox.isChecked = isCheckPokemon
                    checkbox.setOnClickListener {
                        if (checkbox.isChecked) OnCheckListener?.invoke(item.id)
                        else onRemoveCheckListener?.invoke(item.id)

                    }
                }
            }


    }
}



