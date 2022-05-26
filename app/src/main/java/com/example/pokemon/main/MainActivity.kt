package com.example.pokemon.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.pokemon.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.botton_navigation_view)
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment)
        navigationController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navigationController)
    }

}

