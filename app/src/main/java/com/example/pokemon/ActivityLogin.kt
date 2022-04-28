package com.example.pokemon


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.pokemon.MainActivity

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

        btentrar.setOnClickListener {
            when {
                editemail.text.toString().isEmpty() || editsenha.text.toString().isEmpty() ->
                    Toast.makeText(applicationContext, "Prencha os campos", Toast.LENGTH_LONG).show()

                editemail.text.toString() == "juliana@gmail.com" && editsenha.text.toString() == "1234" ->
                    startActivity(Intent(applicationContext, MainActivity::class.java))

                else -> Toast.makeText(applicationContext, "Login Incorreto", Toast.LENGTH_LONG).show()
            }
        }
    }
}
