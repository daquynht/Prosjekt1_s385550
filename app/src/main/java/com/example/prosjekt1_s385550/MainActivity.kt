package com.example.prosjekt1_s385550

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prosjekt1_s385550.ui.screens.GameScreen
import com.example.prosjekt1_s385550.ui.screens.MainMenu
import com.example.prosjekt1_s385550.ui.screens.PrefScreen
import com.example.prosjekt1_s385550.ui.screens.AboutGame
import com.example.prosjekt1_s385550.ui.theme.IntroComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroComposeTheme {
                val navController = rememberNavController()
                val prefViewModel: PrefViewModel = viewModel()

                NavHost(navController = navController, startDestination = "mainMenu") {
                    composable("mainMenu") {
                        MainMenu(
                            prefViewModel = prefViewModel,
                            onStartClick = { navController.navigate("game") },
                            onPrefClick = { navController.navigate("preferences") },
                            onAboutClick = { navController.navigate("about") }
                        )
                    }
                    composable("game") {
                        val gameViewModel: GameViewModel = viewModel()
                        GameScreen(
                            prefViewModel = prefViewModel,
                            gameViewModel = gameViewModel,
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("preferences") {
                        PrefScreen(
                            prefViewModel = prefViewModel,
                            onBack = { navController.popBackStack() }
                        )
                    }
                    composable("about") {
                        AboutGame(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
