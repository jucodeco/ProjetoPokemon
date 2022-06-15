package com.example.pokemon.login

import com.example.pokemon.login.userRepository.UserRepository


open class LoginController ( private val userRepository: UserRepository){

    @Throws(Exception::class, InvalidLoginException::class)
    fun onClikEntrar(email : String, senha : String) {
        when {
            email.isEmpty() ||senha.isEmpty() ->
                throw Exception ("Prencha os campos")

            email== "jucodeco@gmail.com" && senha == "1234" -> {

                userRepository.save(email,senha.toInt())

            }
            else -> throw InvalidLoginException()
        }

    }

    fun getemail():String {

        return userRepository.getEmail( ) ?: ""

    }
    fun getsenha():String{

        val senhaint =  userRepository.getSenha()
        if (senhaint==Int.MIN_VALUE)
           return ""
        else return senhaint.toString()

    }

}

