package com.example.sandbox_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sandbox_compose.core.data.remote.KtorClient
import com.example.sandbox_compose.ui.pages.CharacterDetailsScreen
import com.example.sandbox_compose.ui.theme.RickPrimary
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme

class MainActivity : ComponentActivity() {

    private val ktorClient = KtorClient()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sandbox_composeTheme {
                Scaffold(
                       modifier = Modifier
                              .fillMaxSize(),
                       containerColor = RickPrimary
                ) {
                    CharacterDetailsScreen(
                           // modifier = Modifier.padding(innerPadding),
                           ktorClient = ktorClient,
                           characterId = 2
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Sandbox_composeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CharacterDetailsScreen(
                   modifier = Modifier
                          .fillMaxSize()
                          .padding(innerPadding),
                   ktorClient = KtorClient(),
                   characterId = 1
            )
        }
    }
}
