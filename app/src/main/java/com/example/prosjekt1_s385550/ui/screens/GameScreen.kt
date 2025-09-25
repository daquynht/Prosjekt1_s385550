package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.prosjekt1_s385550.GameViewModel
import com.example.prosjekt1_s385550.PrefViewModel
import com.example.prosjekt1_s385550.ui.components.AppTopBar
import com.example.prosjekt1_s385550.ui.theme.ButtonGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    prefViewModel: PrefViewModel,
    gameViewModel: GameViewModel,
    onBack: () -> Unit
) {
    val numQuestions = prefViewModel.hentPref().toIntOrNull() ?: 5

    // Timer state
    var timeElapsed by remember { mutableStateOf(0) }

    // Start timer
    LaunchedEffect(key1 = gameViewModel.currentQuestion) {
        while (gameViewModel.currentQuestion.isNotEmpty()) {
            delay(1000)
            timeElapsed++
        }
    }

    // Start spillet første gang
    LaunchedEffect(key1 = true) {
        gameViewModel.startGame(numQuestions, prefViewModel)
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "MathBite",
                showBackButton = true,
                onBackClick = onBack
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Timer øverst til høyre
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text("Tid: ${timeElapsed}s", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (gameViewModel.currentQuestion.isEmpty()) {
                    // Spillet ferdig
                    Text(
                        text = gameViewModel.feedback.ifEmpty { "Alle oppgaver er gjort!" },
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            gameViewModel.startGame(numQuestions, prefViewModel)
                            timeElapsed = 0
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Spill på nytt", fontSize = 22.sp, color = Color.White)
                    }

                } else {
                    // Vis spørsmål
                    Text(
                        text = gameViewModel.currentQuestion,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Svarfelt
                    Text(
                        text = "Svar: ${gameViewModel.currentAnswer}",
                        fontSize = 22.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Feedback etter innsending
                    if (gameViewModel.feedback.isNotEmpty()) {
                        Text(
                            text = gameViewModel.feedback,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (gameViewModel.feedback.startsWith("Riktig")) Color.Green else Color.Red
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Antall oppgaver gjort og igjen
                    val totalDone = prefViewModel.hentAntallGjort()
                    Text(
                        text = "Oppgaver gjort totalt: $totalDone\nGjenværende i denne runden: ${gameViewModel.totalQuestions - gameViewModel.questionsAnswered}",
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Knapp-rader (1,2,3..)
                    val buttonRows = listOf(
                        listOf("1","2","3"),
                        listOf("4","5","6"),
                        listOf("7","8","9"),
                        listOf("0","C","Sjekk svar")
                    )

                    buttonRows.forEach { row ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row.forEach { label ->
                                Button(
                                    onClick = {
                                        when(label){
                                            "C" -> gameViewModel.clearAnswer()
                                            "Sjekk svar" -> gameViewModel.checkAnswer(prefViewModel)
                                            else -> gameViewModel.appendDigit(label)
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
                                    modifier = Modifier.weight(1f).height(60.dp),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text(label, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
