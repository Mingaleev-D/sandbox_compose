package com.example.sandbox_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.sandbox_compose.domain.usecase.ReadAppEntryUseCase
import com.example.sandbox_compose.domain.usecase.SaveAppEntryUseCase
import com.example.sandbox_compose.ui.navigation.NavGraphSetup
import com.example.sandbox_compose.ui.pages.onboarding.OnboardingPage
import com.example.sandbox_compose.ui.pages.onboarding.OnboardingViewModel
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()


        setContent {
            Sandbox_composeTheme {
                val viewModelOnBoard: OnboardingViewModel = hiltViewModel()
                NavGraphSetup(
                       startDestination = viewModelOnBoard.startDestination.value
                )
            }
        }
    }
}
