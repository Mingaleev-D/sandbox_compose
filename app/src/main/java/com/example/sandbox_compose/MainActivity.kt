package com.example.sandbox_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sandbox_compose.domain.repository.GamesRepository
import com.example.sandbox_compose.ui.pages.games.GamesPage
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sandbox_composeTheme {
                Scaffold(
                       modifier = Modifier
                           .fillMaxSize()
                ) { paddingValue->
                    Box(
                           modifier = Modifier
                               .fillMaxSize()
                               .background(
                                      brush = Brush.verticalGradient(
                                             colors = listOf(
                                                    Color(0xFF003300), // Темно-зеленый (верх)
                                                    Color(0xFF001A00)  // Еще более темный зеленый (низ)
                                             )
                                      )
                               )
                               .padding(paddingValues = paddingValue)
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                               navController = navController,
                               startDestination = "games"
                        ) {
                            composable("games") {
                                GamesPage()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Sandbox_composeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(text = "Hello")
        }
    }
}
