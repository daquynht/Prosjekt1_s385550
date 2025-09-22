package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.prosjekt1_s385550.GameViewModel
import com.example.prosjekt1_s385550.PrefViewModel
import com.example.prosjekt1_s385550.ui.theme.ButtonGreen
import com.example.prosjekt1_s385550.ui.theme.Knapp1
import com.example.prosjekt1_s385550.ui.theme.Knapp2
import com.example.prosjekt1_s385550.ui.theme.Knapp3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    prefViewModel: PrefViewModel,
    gameViewModel: GameViewModel,
    onBack: () -> Unit
) {
    val numQuestions = prefViewModel.hentPref()?.toIntOrNull() ?: 5

    // Start spillet kun én gang når skjermen vises
    LaunchedEffect(key1 = true) {
        gameViewModel.startGame(numQuestions)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Game Screen") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (gameViewModel.currentQuestion.isNotEmpty()) {
                // Spørsmål og svar
                Text(
                    text = gameViewModel.currentQuestion,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Svar: ${gameViewModel.currentAnswer}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = gameViewModel.feedback,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Kalkulator-rader
                val buttons = listOf(
                    listOf("1","2","3"),
                    listOf("4","5","6"),
                    listOf("7","8","9"),
                    listOf("0","Sjekk svar","C")
                )

                val colors = listOf(
                    listOf(Knapp1, Knapp2, Knapp3),
                    listOf(Knapp2, Knapp3, Knapp1),
                    listOf(Knapp3, Knapp1, Knapp2),
                    listOf(Knapp1, ButtonGreen, Knapp3) // "Sjekk svar" = ButtonGreen
                )

                buttons.forEachIndexed { rowIndex, row ->
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        row.forEachIndexed { colIndex, label ->
                            Button(
                                onClick = {
                                    when(label){
                                        "C" -> gameViewModel.clearAnswer()
                                        "Sjekk svar" -> gameViewModel.checkAnswer()
                                        else -> gameViewModel.appendDigit(label)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = colors[rowIndex][colIndex]),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                            ) {
                                Text(
                                    text = label,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                Text(
                    text = "Spillet er ferdig! Du fikk ${gameViewModel.score} av ${gameViewModel.totalQuestions} riktige!",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}
