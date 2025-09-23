package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.prosjekt1_s385550.PrefViewModel

@Composable
fun PrefScreen(prefViewModel: PrefViewModel, onBack: () -> Unit) {
    // Hent lagret preferanse fra ViewModel (default til "5" hvis tom)
    val savedPref = prefViewModel.hentPref() ?: "5"
    var selectedValue by remember { mutableStateOf(savedPref) }

    val options = listOf("5", "10", "15")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Velg antall oppgaver:", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Radioknapper for 5, 10, 15
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                RadioButton(
                    selected = selectedValue == option,
                    onClick = {
                        selectedValue = option
                        prefViewModel.settPref(option) // lagrer i SharedPreferences
                    }
                )
                Text(option)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Lagret preferanse: $selectedValue")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text("Tilbake")
        }
    }
}
