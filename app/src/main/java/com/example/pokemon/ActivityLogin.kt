package com.example.pokemon

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.pokemon.view.MainActivity
import java.lang.Exception

class ActivityLogin : AppCompatActivity() {

    private lateinit var editemail: EditText
    private lateinit var editsenha: EditText
    private lateinit var btentrar: AppCompatButton
    private lateinit var controller: Controller

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = Controller(getSharedPreferences("Login SharedPreferences", MODE_PRIVATE))
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        editemail = findViewById(R.id.editemail)
        editsenha = findViewById(R.id.editsenha)
        btentrar = findViewById(R.id.btentrar)

        editemail.setText(controller.getemail())
        editsenha.setText(controller.getsenha())


        btentrar.setOnClickListener {
            try {
                controller.onClikEntrar(editemail.text.toString(), editsenha.text.toString())
                startActivity(Intent(applicationContext, MainActivity::class.java))
            } catch (exception: Exception) {
                Toast.makeText(applicationContext, exception.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}













