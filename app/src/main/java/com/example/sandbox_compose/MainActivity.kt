package com.example.sandbox_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sandbox_compose.core.data.remote.KtorClient
import com.example.sandbox_compose.ui.pages.CharacterDetailsScreen
import com.example.sandbox_compose.ui.theme.RickAction
import com.example.sandbox_compose.ui.theme.RickPrimary
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme

class MainActivity : ComponentActivity() {

    private val ktorClient = KtorClient()

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
                    NavHost(navController = navController, startDestination = "character_details") {
                        composable("character_details") {
                            CharacterDetailsScreen(
                                   ktorClient = ktorClient,
                                   characterId = 2
                            ) {
                                navController.navigate("character_episodes/$it")
                            }
                        }
                        composable(
                               route = "character_episodes/{characterId}",
                               arguments = listOf(navArgument("characterId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val characterId = backStackEntry.arguments?.getInt("characterId") ?: -1
                            CharacterEpisodeScreen(characterId = characterId)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterEpisodeScreen(characterId: Int) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Character episode screen: $characterId", fontSize = 28.sp, color = RickAction)
    }
}
