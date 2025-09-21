package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.prosjekt1_s385550.R

@Composable
fun GameScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    // Henter arrays fra res/values/arrays.xml
    val questions = context.resources.getStringArray(R.array.questions)
    val answers = context.resources.getIntArray(R.array.answers)

    // State for spill
    var currentIndex by remember { mutableStateOf(0) }
    var answer by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf("") }
    var usedIndices by remember { mutableStateOf(mutableSetOf<Int>()) }

    // Velger første spørsmål tilfeldig
    if (usedIndices.isEmpty()) {
        currentIndex = questions.indices.random()
        usedIndices.add(currentIndex)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = questions[currentIndex], style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Svar: $answer")
        Spacer(modifier = Modifier.height(16.dp))

        // Tallknapper
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            (0..9).forEach { num ->
                Button(
                    onClick = { answer += num.toString() },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(num.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // OK-knapp
        Button(onClick = {
            val correct = answers[currentIndex]
            feedback = if (answer.toIntOrNull() == correct) {
                "Riktig!"
            } else {
                "Feil, svaret var $correct"
            }
        }) {
            Text("OK")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(feedback)

        Spacer(modifier = Modifier.height(16.dp))

        // Neste-knapp
        Button(onClick = {
            if (usedIndices.size == questions.size) {
                feedback = "Ingen flere spørsmål igjen!"
            } else {
                // trekk et nytt tilfeldig spørsmål
                var nextIndex: Int
                do {
                    nextIndex = questions.indices.random()
                } while (usedIndices.contains(nextIndex))

                currentIndex = nextIndex
                usedIndices.add(nextIndex)
                answer = ""
                feedback = ""
            }
        }) {
            Text("Neste spørsmål")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Tilbake til meny")
        }
    }
}
