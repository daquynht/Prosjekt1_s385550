package com.example.prosjekt1_s385550.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prosjekt1_s385550.GameViewModel
import com.example.prosjekt1_s385550.PrefViewModel
import com.example.prosjekt1_s385550.ui.screens.AboutGame
import com.example.prosjekt1_s385550.ui.screens.GameScreen
import com.example.prosjekt1_s385550.ui.screens.MainMenu
import com.example.prosjekt1_s385550.ui.screens.PrefScreen

@Composable
fun AppNav(prefViewModel: PrefViewModel, gameViewModel: GameViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {

        // MainMenu skjerm
        composable("menu") {
            MainMenu(
                prefViewModel = prefViewModel,
                onStartClick = {
                    // Start spillet med valgt antall spørsmål
                    gameViewModel.startGame(prefViewModel.hentPref().toInt(), prefViewModel)
                    navController.navigate("game")
                },
                onPrefClick = { navController.navigate("pref") },
                onAboutClick = { navController.navigate("about") }
            )
        }

        // GameScreen skjerm
        composable("game") {
            GameScreen(
                gameViewModel = gameViewModel,
                prefViewModel = prefViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        // PrefScreen skjerm
        composable("pref") {
            PrefScreen(
                prefViewModel = prefViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        // AboutGame skjerm
        composable("about") {
            AboutGame(
                onBack = { navController.popBackStack() }
            )
        }
    }
}

