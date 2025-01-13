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
import com.example.sandbox_compose.ui.pages.auth.register.SignUpPage
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme
import com.example.sandbox_compose.utils.Constants

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sandbox_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    SignUpPage(
                           modifier = Modifier.padding(paddingValues = it),
                           onBackButtonClicked = {},
                           onLoginClick = {},
                           onNavigateToLoginScreen = {}
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
    }
}
