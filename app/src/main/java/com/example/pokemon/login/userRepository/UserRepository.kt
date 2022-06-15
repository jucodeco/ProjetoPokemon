package com.example.pokemon.login.userRepository



interface UserRepository {

    fun save (email: String, senha: Int)
    fun getEmail(): String?
   fun getSenha(): Int?

}