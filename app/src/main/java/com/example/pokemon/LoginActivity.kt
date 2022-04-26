package com.example.pokemon
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bt_entrar.setOnClickListener {
            val intent = Intent(this, RespActivity::class.java)
            if (edit_email.text.toString() == "usuario" && edit_senha.text.toString() == "1234"){
                intent.putExtra("result", getString(R.string.login_OK))
            }
            else {
                intent.putExtra("result", getString(R.string.login_ERR))
            }
            startActivity(intent)
        }
    }

}