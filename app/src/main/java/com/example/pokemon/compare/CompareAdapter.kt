package com.example.pokemon.compare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R

class CompareAdapter (private val items: List<PokemonCompareType>?) : RecyclerView.Adapter<CompareAdapter.ViewHolder>() {

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
        fun bindView(item: PokemonCompareType?) {
            item?.let {
                val resistanceType = itemView.findViewById<TextView>(R.id.weaknessResistanceType)

                resistanceType.text = it.name

                itemView.setBackgroundResource(it.color)

            }


        }



    }


}
