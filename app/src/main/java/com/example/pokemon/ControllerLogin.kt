package com.example.pokemon

import  android.content.SharedPreferences


class Controller (private val pref : SharedPreferences){



    //val senha = pref.getInt("SENHA", Int.MIN_VALUE)

    @Throws(Exception::class)
    fun onClikEntrar(email : String, senha : String) {
        when {
            email.isEmpty() ||senha.isEmpty() ->
                throw Exception ("Prencha os campos")

            email== "jucodeco@gmail.com" && senha == "1234" -> {
                val editor = pref.edit()
                editor.putString("EMAIL", email)
                editor.putInt("SENHA", senha.toInt())
                editor.apply()

            }
            else -> throw Exception ("Login Incorreto")
        }



    }

    fun getemail():String {
        return pref.getString("EMAIL", null ) ?: ""

    }

    fun getsenha():String{



        val senhaint =  pref.getInt("SENHA", Int.MIN_VALUE)
        if (senhaint==Int.MIN_VALUE)
            return ""
        else return senhaint.toString()

    }

}