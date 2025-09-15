package com.example.prosjekt1_s385550

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Henter ut arrays fra res
        val questions = resources.getStringArray(R.array.questions)
        val answers = resources.getIntArray(R.array.answers)

        // Tilfeldig spørsmål
        val randomIndex = questions.indices.random()

        // TextViews i layout
        val questionTextView = findViewById<TextView>(R.id.text_question)
        val answerTextView = findViewById<TextView>(R.id.text_answer)

        // Sett spørsmålstekst
        questionTextView.text = questions[randomIndex]

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
                // Legg til tallet i svarfeltet
                val currentAnswer = answerTextView.text.toString()
                answerTextView.text = currentAnswer + button.text
            }
        }

        // OK-knappen
        val enterButton = findViewById<Button>(R.id.button_enter)
        enterButton.setOnClickListener {
            val userAnswer = answerTextView.text.toString().toIntOrNull()

            if (userAnswer == null) {
                answerTextView.text = "Skriv inn et tall!"
            } else if (userAnswer == answers[randomIndex]) {
                answerTextView.text = "Riktig!"
            } else {
                answerTextView.text = "Feil! Svaret var ${answers[randomIndex]}"
            }
        }

        //Clear-knappen
        val clearButton = findViewById<Button>(R.id.button_clear)
        clearButton.setOnClickListener {
            answerTextView.text = ""
        }
    }
}