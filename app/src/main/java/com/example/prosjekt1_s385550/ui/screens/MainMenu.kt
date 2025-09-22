package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prosjekt1_s385550.PrefViewModel

@Composable
fun MainMenu(
    prefViewModel: PrefViewModel,
    onStartClick: () -> Unit,
    onPrefClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onStartClick) {
            Text("Start spill")
        }
        Button(onClick = onPrefClick) {
            Text("Preferanser")
        }
        Button(onClick = onAboutClick) {
            Text("Om spillet")
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenuPreview() {
    // Lag en fake PrefViewModel for å tilfredsstille parameteret
    val fakePrefViewModel = PrefViewModel()

    MainMenu(
        prefViewModel = fakePrefViewModel,
        onStartClick = {},
        onPrefClick = {},
        onAboutClick = {}
    )
}