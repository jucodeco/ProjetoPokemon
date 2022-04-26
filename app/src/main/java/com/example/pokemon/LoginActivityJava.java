package com.example.pokemon;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivityJava extends AppCompatActivity {

    EditText edit_email, edit_senha;
    Button bt_entrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_entrar = findViewById(R.id.bt_entrar);

        edit_email.requestFocus();

        bt_entrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (edit_email.getText().toString().isEmpty() || edit_senha.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Prencha os campos", Toast.LENGTH_LONG).show();

                    edit_email.requestFocus();

                } else if (edit_email.getText().toString().equals("juliana@gmail.com") &&
                        edit_senha.getText().toString().equals ("1234")){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Login Incorreto", Toast.LENGTH_LONG).show();

                    edit_email.requestFocus();
                }
            }
        });

    }

}

