package com.example.pokemon.login.userRepository

import android.content.SharedPreferences

class UserRepositoryImpl(private val pref: SharedPreferences) : UserRepository {
    override fun save(email: String, senha: Int) {
        val editor = pref.edit()
        editor.putString("EMAIL", email)
        editor.putInt("SENHA", senha)
        editor.apply()
    }

    override fun getEmail(): String ?{
        return pref.getString("EMAIL", null)
    }

    override fun getSenha(): Int {
        return pref.getInt("SENHA", Int.MIN_VALUE)
    }
}