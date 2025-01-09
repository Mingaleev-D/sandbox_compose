package com.example.sandbox_compose.ui.pages.user_auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sandbox_compose.NavDestination
import com.example.sandbox_compose.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterPage(navController: NavController, viewModel: RegisterViewModel = koinViewModel()) {

    val loginState = viewModel.registerState.collectAsState()
    Column(
           modifier = Modifier
               .fillMaxSize(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = loginState.value) {
            is RegisterState.Success -> {
                LaunchedEffect(loginState.value) {
                    navController.navigate(NavDestination.Home.route) {
                        popUpTo(NavDestination.Home.route) {
                            inclusive = true
                        }
                    }
                }
            }

            is RegisterState.Error -> {
                Text(text = state.message)
                // Show error message
            }

            is RegisterState.Loading -> {
                CircularProgressIndicator()
                Text(text = "loading")
            }

            else -> {
                RegisterContent(onRegisterClicked = { email, password, name ->
                    viewModel.register(email = email, password = password, name = name)
                },
                                onSignInClick = {
                                    navController.popBackStack()
                                })
            }
        }
    }
}


@Composable
fun RegisterContent(
       onRegisterClicked: (String, String, String) -> Unit,
       onSignInClick: () -> Unit
) {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }
    Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(16.dp),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
               painter = painterResource(R.drawable.scale_1200),
               contentScale = ContentScale.Fit,
               contentDescription = null,
               modifier = Modifier.size(190.dp),
        )

        Text(text = "register", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
               value = name.value,
               leadingIcon = {
                   Icon(
                          painter = painterResource(R.drawable.ic_person_24),
                          contentDescription = null,
                          modifier = Modifier.size(24.dp)
                   )
               },
               onValueChange = {
                   name.value = it
               },
               modifier = Modifier
                   .padding(vertical = 4.dp)
                   .fillMaxWidth(),
               label = { Text(text = "name") }
        )

        OutlinedTextField(
               value = email.value,
               leadingIcon = {
                   Icon(
                          painter = painterResource(R.drawable.baseline_mail_24),
                          contentDescription = null,
                          modifier = Modifier.size(24.dp)
                   )
               },
               onValueChange = {
                   email.value = it
               },
               modifier = Modifier
                   .padding(vertical = 4.dp)
                   .fillMaxWidth(),
               label = { Text(text = "email") }
        )
        OutlinedTextField(
               value = password.value,
               leadingIcon = {
                   Icon(
                          painter = painterResource(R.drawable.baseline_password_24),
                          contentDescription = null,
                          modifier = Modifier.size(24.dp)
                   )
               },
               onValueChange = {
                   password.value = it
               },
               modifier = Modifier
                   .padding(vertical = 8.dp)
                   .fillMaxWidth(),
               label = { Text(text = "password") },
               visualTransformation = PasswordVisualTransformation()
        )
        Button(
               onClick = {
                   onRegisterClicked(email.value, password.value, name.value)
               }, modifier = Modifier.fillMaxWidth(),
               enabled = email.value.isNotEmpty() && password.value.isNotEmpty() && name.value.isNotEmpty()
        ) {
            Text(text = "register")
        }
        Text(text = "alread_have_an_account", modifier = Modifier
            .padding(8.dp)
            .clickable {
                onSignInClick()
            })
    }
}
