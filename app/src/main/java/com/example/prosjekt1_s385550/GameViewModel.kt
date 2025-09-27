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

    var score by mutableStateOf(0)                // Antall riktige denne runden
    var questionsAnswered by mutableStateOf(0)    // Antall spørsmål besvart denne runden
    var totalQuestions by mutableStateOf(0)       // Antall spørsmål i runden

    val usedIndices = mutableSetOf<Int>()         // Holder styr på totalt brukte spørsmål

    // Start ny runde med valgt antall spørsmål
    fun startGame(numQuestions: Int, prefViewModel: PrefViewModel) {
        totalQuestions = numQuestions
        score = 0
        questionsAnswered = 0

        // hent hvor mange oppgaver som allerede er gjort totalt
        val antallGjort = prefViewModel.hentAntallGjort()
        usedIndices.clear()
        for (i in 0 until antallGjort) {
            usedIndices.add(i)
        }

        loadNewQuestion(prefViewModel)
    }

    // Hent neste spørsmål som ikke er brukt
    fun loadNewQuestion(prefViewModel: PrefViewModel) {
        if (questionsAnswered >= totalQuestions || usedIndices.size >= questionsArray.size) {
            currentQuestion = ""
            currentAnswer = ""
            feedback = ""
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

    //Sjekk svar, og passer på at brukerinput ikke er null
    fun checkAnswer(prefViewModel: PrefViewModel) {
        val userAnswer = currentAnswer.toIntOrNull()
        if (userAnswer == null) {
            feedback = "Skriv inn et tall!"
            return
        }

        if (userAnswer == answersArray[currentIndex]) {
            score++
            feedback = "Riktig! ✅"
        } else {
            feedback = "Feil! ❌ Svaret var ${answersArray[currentIndex]}"
        }

        questionsAnswered++  // Flyttes etter feedback

        // Oppdater totalt antall oppgaver gjort
        val totalDone = prefViewModel.hentAntallGjort() + 1
        prefViewModel.settAntallGjort(totalDone)
    }

    // Prøv igjen denne runden
    fun tryAgain(prefViewModel: PrefViewModel) {
        score = 0
        questionsAnswered = 0
        loadNewQuestion(prefViewModel) // trekker fra de som er igjen
    }

    /** Sjekk om en ny runde er mulig */
    fun canTryAgain(): Boolean {
        val brukt = usedIndices.size
        val totalt = questionsArray.size
        return brukt + totalQuestions <= totalt
    }

    //Resetter alt for å starte en ny runde
    fun restart(prefViewModel: PrefViewModel) {
        score = 0
        questionsAnswered = 0
        prefViewModel.settAntallGjort(0)
        usedIndices.clear()
        loadNewQuestion(prefViewModel)
    }
}


