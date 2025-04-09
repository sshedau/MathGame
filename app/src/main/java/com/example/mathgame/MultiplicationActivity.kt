package com.example.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import kotlin.random.Random

class MultiplicationActivity : AppCompatActivity() {

    lateinit var textViewScoreValue : TextView
    lateinit var textViewLifeValue : TextView
    lateinit var textViewTimeValue : TextView

    lateinit var textViewQuestion : TextView
    lateinit var editTextAnswer : EditText

    lateinit var buttonOk : Button
    lateinit var buttonNext : Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer : CountDownTimer
    private val startTimerinMillis : Long = 60000
    var timeLeftInMillis : Long = startTimerinMillis

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplication)

        // supportActionBar!!.title = "Multiplication"

        textViewScoreValue = findViewById(R.id.textViewScoreValue)
        textViewLifeValue = findViewById(R.id.textViewLifeValue)
        textViewTimeValue = findViewById(R.id.textViewTimeValue)

        textViewQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)

        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        multiplicationGameContinue()

        buttonOk.setOnClickListener {

            var input = editTextAnswer.text.toString()

            if(input == "") {
                Toast.makeText(applicationContext, "Please write an answer or click the next button .", Toast.LENGTH_LONG).show()
            }
            else {
                pauseTimer()
                var userAnswer = input.toInt()
                if(userAnswer == correctAnswer) {
                    userAnswer += 10
                    textViewQuestion.text = "Congratulations ! Your answer is correct ."
                    textViewScoreValue.text = userAnswer.toString()
                }
                else {
                    userLife--
                    textViewLifeValue.text = userLife.toString()
                    textViewQuestion.text = "Sorry ! Your answer is wrong ."
                }
            }

        }

        buttonNext.setOnClickListener {

            pauseTimer()
            resetTimer()
            editTextAnswer.setText("")

            if(userLife == 0) {
                Toast.makeText(applicationContext, "Game Over !", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MultiplicationActivity, ResultActivity::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()      // When ResultActivity will open, the GameActivity will close . It will not be at the back, or below in the STACK .
            }
            else {
                multiplicationGameContinue()
            }

        }

    }

    fun multiplicationGameContinue() {
        var num1 = Random.nextInt(0, 100)
        var num2 = Random.nextInt(0, 100)
        textViewQuestion.text = "$num1 * $num2"
        correctAnswer = num1 * num2
        startTimer()
    }

    fun startTimer() {

        timer = object : CountDownTimer(timeLeftInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLife--
                textViewLifeValue.text = userLife.toString()
                textViewQuestion.text = "Sorry ! Your time is up ."

            }

        }
    }

    fun updateText() {
        val remainingTime : Int = (timeLeftInMillis /1000).toInt()
        textViewTimeValue.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {
        timeLeftInMillis = startTimerinMillis
        updateText()
    }


}