package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var buttonAddition : Button
    lateinit var buttonSubtraction : Button
    lateinit var buttonMultiplication : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonAddition = findViewById(R.id.buttonAddition)
        buttonSubtraction = findViewById(R.id.buttonSubtraction)
        buttonMultiplication = findViewById(R.id.buttonMultiplication)

        buttonAddition.setOnClickListener {
            val intent = Intent(this@MainActivity, AdditionActivity::class.java)
            startActivity(intent)
        }

        buttonSubtraction.setOnClickListener {
            val intent = Intent(this@MainActivity, SubtractionActivity::class.java)
            startActivity(intent)
        }

        buttonMultiplication.setOnClickListener {
            val intent = Intent(this@MainActivity, MultiplicationActivity::class.java)
            startActivity(intent)
        }


    }
}