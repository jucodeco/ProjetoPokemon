package com.example.pokemon.login


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.pokemon.R
import com.example.pokemon.login.ui.theme.PokemonTheme
import com.example.pokemon.login.userRepository.UserRepositoryImpl
import com.example.pokemon.main.MainActivity
import java.lang.Exception


class ActivityLoginCompose : ComponentActivity() {


    private lateinit var controllerLogin: LoginController

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controllerLogin = LoginController(UserRepositoryImpl(getSharedPreferences("Login SharedPreferences", MODE_PRIVATE)))
        setContent {
            PokemonTheme {
                LoginPokemon()
            }
        }
    }
    @Composable
    fun LoginPokemon() {

        val focusManager = LocalFocusManager.current
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }


        val isPasswordValid by derivedStateOf {
            password.length > 4
        }
        var isPasswordVisible by remember {
            mutableStateOf(false)
        }

       Column(
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top


        )
        {
            Image(
                painter = painterResource(id = R.drawable.logopokebola),
                contentDescription = "Pokebola",
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 25.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.Red)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(all = 10.dp)
                ) {
                    OutlinedTextField(value = email, onValueChange = { email = it },
                        label = { Text("E-mail") },
                        placeholder = { Text("abc@domain.com") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        trailingIcon = {
                            if (email.isNotBlank()){
                                IconButton(onClick = { email = "" }) {
                                Icon (
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Clean Email "
                                )


                                }
                            }
                        }



                    )

                    OutlinedTextField(value = password, onValueChange = { password = it },
                        label = { Text("Senha") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.clearFocus() }
                        ),
                        isError = !isPasswordValid,
                        trailingIcon = {
                            IconButton(onClick = {isPasswordVisible = !isPasswordVisible}) {
                                Icon(imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription ="password" )

                            }
                        },

                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation ()

                    )



                    Button(
                        onClick =  { onClickButtom (email,password)




                        },
                        enabled = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text(
                            text = "Entrar",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }


                }
            }


        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PokemonTheme {
            LoginPokemon()
        }
    }

    private fun onClickButtom  (email: String, senha: String){
        try {
            controllerLogin.onClikEntrar(email,senha)
            startActivity(Intent(applicationContext, MainActivity::class.java))
        } catch (exception: Exception) {
            Toast.makeText(applicationContext, exception.message, Toast.LENGTH_LONG).show()
        }
    }
}




























