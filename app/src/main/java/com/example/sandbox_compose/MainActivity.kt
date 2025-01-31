package com.example.sandbox_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sandbox_compose.ui.navigation.NavGraphSetup
import com.example.sandbox_compose.ui.navigation.NavigationActions
import com.example.sandbox_compose.ui.navigation.Routes
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sandbox_compose.ui.theme.defaultSpacing

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sandbox_composeTheme {
                val navController = rememberNavController()
                val navActions = remember {
                    NavigationActions(navController)
                }
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute =
                       navBackStackEntry?.destination?.route ?: Routes.HomePage().route
                val scope = rememberCoroutineScope()


                ModalNavigationDrawer(
                       drawerContent = {
                           AppDrawerContent(
                                  currentRoute = currentRoute,
                                  navigateToHome = navActions.navigateToHomePage,
                                  navigateToSetting = navActions.navigateToSettingsPage,
                                  navigateToFavouriteScreen = navActions.navigateToFavoritePage,
                                  closeDrawer = {
                                      scope.launch {
                                          drawerState.close()
                                      }
                                  }
                           )
                       },
                       drawerState = drawerState
                ) {
                    NavGraphSetup(
                           navController = navController,
                           navActions = navActions,
                           openDrawer = {
                               scope.launch {
                                   drawerState.open()
                               }
                           }
                    )
                }
            }
        }
    }
}

@Composable
fun AppDrawerContent(
       currentRoute: String,
       navigateToHome: () -> Unit,
       navigateToSetting: () -> Unit,
       navigateToFavouriteScreen: () -> Unit,
       closeDrawer: () -> Unit,
       modifier: Modifier = Modifier,
) {
    ModalDrawerSheet(modifier) {
        NewsyLogo(
               modifier = Modifier
                   .padding(
                          horizontal = 28.dp,
                          vertical = 24.dp
                   )
        )

        NavigationDrawerItem(
               label = { Text("Home") },
               icon = { Icon(imageVector = Icons.Filled.Home, null) },
               selected = currentRoute == Routes.HomePage().route,
               onClick = { navigateToHome();closeDrawer() },
               modifier = Modifier.padding(
                      NavigationDrawerItemDefaults.ItemPadding
               )
        )

        NavigationDrawerItem(
               label = { Text("Favourite") },
               icon = { Icon(imageVector = Icons.Filled.Bookmark, null) },
               selected = currentRoute == Routes.FavoritesPage().route,
               onClick = { navigateToFavouriteScreen();closeDrawer() },
               modifier = Modifier.padding(
                      NavigationDrawerItemDefaults.ItemPadding
               )
        )

        NavigationDrawerItem(
               label = { Text("Settings") },
               icon = { Icon(imageVector = Icons.Filled.Home, null) },
               selected = currentRoute == Routes.SettingsPage().route,
               onClick = { navigateToSetting();closeDrawer() },
               modifier = Modifier.padding(
                      NavigationDrawerItemDefaults.ItemPadding
               )
        )
    }

}

@Composable
fun NewsyLogo(
       modifier: Modifier = Modifier,
) {
    Row(
           modifier = modifier,
           horizontalArrangement = Arrangement.Center,
           verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
               painter = painterResource(R.drawable.ic_newsy_logo),
               contentDescription = null,
               tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(defaultSpacing))
        Icon(
               painterResource(R.drawable.ic_newsy_water_mark),
               contentDescription = null,
               tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
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
