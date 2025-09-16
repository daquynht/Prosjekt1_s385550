package com.example.prosjekt1_s385550

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class GameActivity : AppCompatActivity() {

    private lateinit var questions: Array<String>
    private lateinit var answers: IntArray
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Henter ut arrays fra res
        questions = resources.getStringArray(R.array.questions)
        answers = resources.getIntArray(R.array.answers)

        // TextViews i layout
        val questionTextView = findViewById<TextView>(R.id.text_question)
        val answerTextView = findViewById<TextView>(R.id.text_answer)
        val feedbackTextView = findViewById<TextView>(R.id.text_feedback) // ✅ Ny TextView

        // Last første spørsmål
        loadNewQuestion(questionTextView, answerTextView, feedbackTextView)

        // Tallknapper 0 til 9
        val buttons = listOf(
            findViewById<Button>(R.id.button_0),
            findViewById<Button>(R.id.button_1),
            findViewById<Button>(R.id.button_2),
            findViewById<Button>(R.id.button_3),
            findViewById<Button>(R.id.button_4),
            findViewById<Button>(R.id.button_5),
            findViewById<Button>(R.id.button_6),
            findViewById<Button>(R.id.button_7),
            findViewById<Button>(R.id.button_8),
            findViewById<Button>(R.id.button_9),
        )

        for (button in buttons) {
            button.setOnClickListener {
                val currentAnswer = answerTextView.text.toString()
                answerTextView.text = currentAnswer + button.text
            }
        }

        // OK-knappen
        val enterButton = findViewById<Button>(R.id.button_enter)

        enterButton.setOnClickListener {
            val userAnswer = answerTextView.text.toString().toIntOrNull()

            if (userAnswer == null) {
                feedbackTextView.setTextColor(getColor(android.R.color.holo_red_dark))
                feedbackTextView.text = "Skriv inn et tall!"
            } else if (userAnswer == answers[currentIndex]) {
                feedbackTextView.setTextColor(getColor(android.R.color.holo_green_dark))
                feedbackTextView.text = "Riktig!"
            } else {
                feedbackTextView.setTextColor(getColor(android.R.color.holo_red_dark))
                feedbackTextView.text = "Feil! Svaret var ${answers[currentIndex]}"
            }
        }

        // Clear-knappen
        val clearButton = findViewById<Button>(R.id.button_clear)
        clearButton.setOnClickListener {
            answerTextView.text = ""
        }

        // Neste-knappen
        val nextButton = findViewById<Button>(R.id.button_next)
        nextButton.setOnClickListener {
            loadNewQuestion(questionTextView, answerTextView, feedbackTextView)
        }
    }

    private fun loadNewQuestion(
        questionTextView: TextView,
        answerTextView: TextView,
        feedbackTextView: TextView
    ) {
        currentIndex = questions.indices.random()
        questionTextView.text = questions[currentIndex]
        answerTextView.text = ""
        feedbackTextView.text = ""
    }
}

