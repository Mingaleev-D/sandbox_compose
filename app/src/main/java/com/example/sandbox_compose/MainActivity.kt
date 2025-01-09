package com.example.sandbox_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sandbox_compose.ui.pages.home.HomePage
import com.example.sandbox_compose.ui.pages.home.HomeViewModel
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sandbox_composeTheme {
                val viewModel = viewModel<HomeViewModel>()
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

                Scaffold (
                       modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
                ){
                    HomePage(
                           scrollBehavior = scrollBehavior,
                           images = viewModel.images,
                           onImageClick = {

                           },
                           onSearchClick = {},
                           onFABClick =     {}

                    )
                }

            }
        }
    }
}
