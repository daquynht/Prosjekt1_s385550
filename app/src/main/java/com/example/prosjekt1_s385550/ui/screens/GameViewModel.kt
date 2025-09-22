package com.example.prosjekt1_s385550.ui.screens

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
    var currentQuestion by mutableStateOf(questionsArray[0])

    private val usedIndices = mutableSetOf<Int>()

    fun loadNewQuestion() {
        if (usedIndices.size == questionsArray.size) {
            feedback = "Ingen flere spørsmål igjen!"
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
        } else if (userAnswer == answersArray[currentIndex]) {
            feedback = "Riktig!"
        } else {
            feedback = "Feil! Svaret var ${answersArray[currentIndex]}"
        }
    }
}
