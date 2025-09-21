import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
                            onAboutClick = { navController.navigate("about") } // vi lager senere
                        )
                    }
                    composable("game") {
                        GameScreen(prefViewModel = prefViewModel, onBack = { navController.popBackStack() })
                    }
                    composable("preferences") {
                        PrefScreen(prefViewModel = prefViewModel, onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}
