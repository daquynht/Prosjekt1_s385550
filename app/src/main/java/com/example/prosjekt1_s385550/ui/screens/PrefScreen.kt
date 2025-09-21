package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.prosjekt1_s385550.PrefViewModel

@Composable
fun PrefScreen(prefViewModel: PrefViewModel, onBack: () -> Unit) {
    val prefValue = prefViewModel.hentPref() ?: ""
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Dine preferanser: $prefValue")
        Button(onClick = onBack) {
            Text("Tilbake")
        }
    }
}

