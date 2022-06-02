package com.example.pokemon

object ColorType {
    fun getcolortype(name: String): Int {
        return when (name) {
            "fire" -> R.color.colorfire
            "poison" -> R.color.colorpoison
            "flying" -> R.color.colorflying
            "water" -> R.color.colorwater
            "grass" -> R.color.colorgrass
            "bug" -> R.color.colorbug
            "normal" -> R.color.colornormal
            "electric" -> R.color.colorelectric
            "ground" -> R.color.colorground
            "fairy" -> R.color.colorfairy
            "fighting" -> R.color.colorfighting
            "psychic" -> R.color.colorpsychic
            "rock" -> R.color.colorrock
            "ice" -> R.color.colorice
            "ghost" -> R.color.colorghost
            "dragon" -> R.color.colordragon
            "dark" -> R.color.colordark
            else -> R.color.gelo
        }
    }
}