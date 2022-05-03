package com.example.pokemon


import android.content.Intent
import android.os.Bundle

import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


@Suppress("UNREACHABLE_CODE")
class ActivityLogin : AppCompatActivity() {

    private lateinit var editemail: EditText
    private lateinit var editsenha: EditText
    private lateinit var btentrar: AppCompatButton

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        editemail = findViewById(R.id.editemail)
        editsenha = findViewById(R.id.editsenha)
        btentrar = findViewById(R.id.btentrar)
        val pref = getPreferences(MODE_PRIVATE)
        val email = pref.getString("EMAIL", null )
        editemail.setText(email ?: "")
        val senha = pref.getInt("SENHA", Int.MIN_VALUE)
        editsenha.setText(if (senha == Int.MIN_VALUE) "" else senha.toString())
        btentrar.setOnClickListener {
            when {
                editemail.text.toString().isEmpty() || editsenha.text.toString().isEmpty() ->
                    Toast.makeText(applicationContext, "Prencha os campos", Toast.LENGTH_LONG).show()

                editemail.text.toString() == "jucodeco@gmail.com" && editsenha.text.toString() == "1234" -> {
                    val editor = pref.edit()
                    editor.putString("EMAIL", editemail.text.toString())
                    editor.putInt("SENHA", editsenha.text.toString().toInt())
                    editor.apply()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
                else -> Toast.makeText(applicationContext, "Login Incorreto", Toast.LENGTH_LONG).show()
            }


        }


    }
}













