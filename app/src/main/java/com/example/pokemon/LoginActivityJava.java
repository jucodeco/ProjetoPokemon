package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivityJava extends AppCompatActivity {

    EditText editemail, editsenha;
    Button btentrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        editemail = findViewById(R.id.editemail);
        editsenha = findViewById(R.id.editsenha);
        btentrar = findViewById(R.id.btentrar);

        editemail.requestFocus();

        btentrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (editemail.getText().toString().isEmpty() || editsenha.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Prencha os campos", Toast.LENGTH_LONG).show();

                    editemail.requestFocus();

                } else if (editemail.getText().toString().equals("juliana@gmail.com") &&
                        editsenha.getText().toString().equals ("1234")){
                    Intent intent = new Intent(getApplicationContext(), MainActivityJava.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Login Incorreto", Toast.LENGTH_LONG).show();

                    editemail.requestFocus();
                }
            }
        });

    }

}

