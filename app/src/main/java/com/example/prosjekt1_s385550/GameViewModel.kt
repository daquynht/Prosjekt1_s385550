package com.example.prosjekt1_s385550

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val resources = application.resources
    private val questionsArray = resources.getStringArray(R.array.questions)
    private val answersArray = resources.getIntArray(R.array.answers)

    var currentIndex by mutableStateOf(0)
    var currentAnswer by mutableStateOf("")
    var feedback by mutableStateOf("")
    var currentQuestion by mutableStateOf("")

    var score by mutableStateOf(0)
    var questionsAnswered by mutableStateOf(0)
    var totalQuestions by mutableStateOf(0)

    private val usedIndices = mutableSetOf<Int>()

    /** Starter et nytt spill med valgt antall spørsmål */
    fun startGame(numQuestions: Int) {
        totalQuestions = numQuestions
        score = 0
        questionsAnswered = 0
        usedIndices.clear()
        loadNewQuestion()
    }

    fun loadNewQuestion() {
        if (questionsAnswered >= totalQuestions) {
            feedback = "Spillet er ferdig! Du fikk $score av $totalQuestions riktige 🎉"
            currentQuestion = ""
            return
        }

        var index: Int
        do {
            index = questionsArray.indices.random()
        } while (usedIndices.contains(index))

        currentIndex = index
        currentQuestion = questionsArray[index]
        currentAnswer = ""
        feedback = ""
        usedIndices.add(index)
    }

    fun appendDigit(digit: String) {
        currentAnswer += digit
    }

    fun clearAnswer() {
        currentAnswer = ""
    }

    fun checkAnswer() {
        val userAnswer = currentAnswer.toIntOrNull()
        if (userAnswer == null) {
            feedback = "Skriv inn et tall!"
            return
        }

        questionsAnswered++

        if (userAnswer == answersArray[currentIndex]) {
            score++
            feedback = "Riktig! 🎉"
        } else {
            feedback = "Feil! Svaret var ${answersArray[currentIndex]}"
        }

        // Last neste spørsmål hvis ikke ferdig
        loadNewQuestion()
    }
}
