package com.example.prosjekt1_s385550

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.prosjekt1_s385550.ui.navigation.AppNav
import com.example.prosjekt1_s385550.ui.theme.IntroComposeTheme

class MainActivity : ComponentActivity() {

    // ViewModels for preferanser og spill
    private val prefViewModel: PrefViewModel by viewModels()
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroComposeTheme {
                // Start navigasjonen
                AppNav(
                    prefViewModel = prefViewModel,
                    gameViewModel = gameViewModel
                )
            }
        }
    }
}
