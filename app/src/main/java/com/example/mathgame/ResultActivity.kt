package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    lateinit var textViewFinalScore : TextView
    lateinit var buttonPlayAgain : Button
    lateinit var buttonExit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        textViewFinalScore = findViewById(R.id.textViewFinalScore)
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain)
        buttonExit = findViewById(R.id.buttonExit)

        val score = intent.getIntExtra("score", 0)
        textViewFinalScore.text = "Your score : " + score

        buttonPlayAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonExit.setOnClickListener {
            // Closing the application .
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

    }

}