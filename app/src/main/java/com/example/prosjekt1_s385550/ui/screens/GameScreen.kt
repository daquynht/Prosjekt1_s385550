package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prosjekt1_s385550.GameViewModel
import com.example.prosjekt1_s385550.PrefViewModel
import com.example.prosjekt1_s385550.ui.components.AppTopBar
import com.example.prosjekt1_s385550.ui.components.MinDialog
import com.example.prosjekt1_s385550.ui.theme.ButtonGreen
import com.example.prosjekt1_s385550.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
    prefViewModel: PrefViewModel,
    onBack: () -> Unit
) {
    var showDialog = remember { mutableStateOf(false) }

    val question = gameViewModel.currentQuestion
    val answer = gameViewModel.currentAnswer
    val feedback = gameViewModel.feedback
    val questionsAnswered = gameViewModel.questionsAnswered
    val totalQuestions = gameViewModel.totalQuestions
    val score = gameViewModel.score
    val userAnswerColor = Color(0xFF0D47A1)

    Scaffold(
        topBar = {
            AppTopBar(
                title = "MathBite",
                showBackButton = true,
                onBackClick = { showDialog.value = true }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Progress bar
            LinearProgressIndicator(
            progress = { if (totalQuestions > 0) questionsAnswered / totalQuestions.toFloat() else 0f },
            modifier = Modifier
                                .fillMaxWidth()
                                .height(12.dp)
                                .clip(RoundedCornerShape(6.dp)),
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Spørsmål + svar
            Card(
                modifier = Modifier.fillMaxWidth().height(180.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB)),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (question.isNotEmpty()) {
                        Row {
                            Text(
                                text = "$question = ",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Text(
                                text = answer,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = userAnswerColor // mørk blå for brukerens input
                            )
                        }
                    } else {
                        Text(
                            text = "Ingen flere spørsmål!",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Feedback underveis
            if (question.isNotEmpty() && feedback.isNotEmpty()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = feedback,
                        fontSize = 24.sp,
                        color = if (feedback.contains("Riktig")) Color(0xFF1B5E20) else Color.Red
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { gameViewModel.loadNewQuestion(prefViewModel) },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text( text = stringResource(id = R.string.next), color = Color.White, fontSize = 20.sp)
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Tastatur eller knapper
            if (question.isNotEmpty()) {
                val rows = listOf(
                    listOf("1", "2", "3"),
                    listOf("4", "5", "6"),
                    listOf("7", "8", "9"),
                    listOf("C", "0", "=")
                )
                rows.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        row.forEach { key ->
                            val color = when (key) {
                                "C" -> Color.Red
                                "=" -> ButtonGreen  // erstatter tidligere ✔
                                else -> Color(0xFF64B5F6)
                            }

                            val onClickAction = when (key) {
                                "C" -> {
                                    { gameViewModel.clearAnswer() }
                                }

                                "=" -> {
                                    { gameViewModel.checkAnswer(prefViewModel) }
                                } // Enter-funksjon
                                else -> {
                                    { gameViewModel.appendDigit(key) }
                                }
                            }

                            Button(
                                onClick = onClickAction,
                                modifier = Modifier
                                    .size(80.dp)  // alle like store
                                    .padding(4.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = color,
                                    contentColor = Color.White
                                ),
                                elevation = ButtonDefaults.buttonElevation(6.dp)
                            ) {
                                Text(key, fontSize = 24.sp)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            } else {
                // Spill ferdig: Vis score og knapp
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Du fikk $score av $questionsAnswered riktige!",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    if (gameViewModel.canTryAgain()) {
                        Button(
                            onClick = { gameViewModel.tryAgain(prefViewModel) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                        ) {
                            Text(text = stringResource(id = R.string.try_again), fontSize = 22.sp, color = Color.White)
                        }
                    } else {
                        Button(
                            onClick = { gameViewModel.restart(prefViewModel) },
                            colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                        ) {
                            Text(stringResource(id = R.string.restart), fontSize = 22.sp, color = Color.White)
                        }
                    }
                }
            }
        }
        if (showDialog.value) {
            MinDialog(
                onDismissRequest = { showDialog.value = false },
                onConfirmation = {
                    showDialog.value = false
                    onBack() // kaller MainMenu
                },
                dialogTitle = stringResource (id = R.string.exit_gamle_title),
                dialogText = stringResource(id = R.string.exit_game_text)
            )
        }
    }
}
