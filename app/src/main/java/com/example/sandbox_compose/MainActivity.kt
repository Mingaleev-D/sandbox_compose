package com.example.sandbox_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sandbox_compose.core.data.remote.KtorClient
import com.example.sandbox_compose.ui.pages.CharacterDetailsScreen
import com.example.sandbox_compose.ui.pages.CharacterEpisodeScreen
import com.example.sandbox_compose.ui.pages.HomeScreen
import com.example.sandbox_compose.ui.theme.RickPrimary
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var ktorClient: KtorClient

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            Sandbox_composeTheme {
                Scaffold(
                       modifier = Modifier
                              .fillMaxSize(),
                       containerColor = RickPrimary
                ) {
                    NavHost(navController = navController, startDestination = "home_screen") {

                        composable(route = "home_screen") {
                            HomeScreen(onCharacterSelected = { characterId ->
                                navController.navigate("character_details/$characterId")
                            })
                        }

                        composable(
                               route = "character_details/{characterId}",
                               arguments = listOf(navArgument("characterId") {
                                   type = NavType.IntType
                               })
                        ) { backStackEntry ->
                            val characterId: Int =
                                   backStackEntry.arguments?.getInt("characterId") ?: -1
                            CharacterDetailsScreen(characterId = characterId) {
                                navController.navigate("character_episodes/$it")
                            }
                        }
                        composable(
                               route = "character_episodes/{characterId}",
                               arguments = listOf(navArgument("characterId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val characterId = backStackEntry.arguments?.getInt("characterId") ?: -1
                            CharacterEpisodeScreen(
                                   characterId = characterId,
                                   ktorClient = ktorClient,
                            )
                        }
                    }
                }
            }
        }
    }
}
//@Composable
//fun CharacterEpisodeScreen(characterId: Int) {
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Text(text = "Character episode screen: $characterId", fontSize = 28.sp, color = RickAction)
//    }
//}
