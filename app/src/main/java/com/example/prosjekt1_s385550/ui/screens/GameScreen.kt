package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.prosjekt1_s385550.GameViewModel
import com.example.prosjekt1_s385550.PrefViewModel

@Preview(showBackground = true)
@Composable
fun GameScreen(
    prefViewModel: PrefViewModel,
    gameViewModel: GameViewModel,
    onBack: () -> Unit
) {
    // Hent state fra GameViewModel
    val score by gameViewModel.score.collectAsStateWithLifecycle()
    val level by gameViewModel.level.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Game Screen") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
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
            Text(text = "Score: $score", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Level: $level", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { gameViewModel.increaseScore() }) {
                Text("Increase Score")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { gameViewModel.nextLevel() }) {
                Text("Next Level")
            }
        }
    }
}

