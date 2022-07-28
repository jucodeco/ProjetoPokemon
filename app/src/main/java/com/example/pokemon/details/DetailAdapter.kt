package com.example.pokemon.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R


class DetailAdapter(private val items: List<PokemonDetailsType>?) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.type_weakness_resistance, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun bindView(item: PokemonDetailsType? ) {
            item?.let {
          val resistanceType = itemView.findViewById<TextView>(R.id.weaknessResistanceType)

            resistanceType.text = it.name

            itemView.setBackgroundResource(it.color)

            }


        }



    }


}


