package com.example.sandbox_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.sandbox_compose.ui.navigator.ProductDetails
import com.example.sandbox_compose.ui.navigator.UiProductModel
import com.example.sandbox_compose.ui.navigator.UserAddressRoute
import com.example.sandbox_compose.ui.navigator.UserAddressRouteWrapper
import com.example.sandbox_compose.ui.navigator.productNavType
import com.example.sandbox_compose.ui.navigator.userAddressNavType
import com.example.sandbox_compose.ui.pages.cart.CartPage
import com.example.sandbox_compose.ui.pages.cart.CartSummaryPage
import com.example.sandbox_compose.ui.pages.cart.user_address.UserAddressPage
import com.example.sandbox_compose.ui.pages.home_products.HomePage
import com.example.sandbox_compose.ui.pages.home_products.ProductDetailsPage
import com.example.sandbox_compose.ui.pages.order.OrdersPage
import com.example.sandbox_compose.ui.pages.user_auth.LoginPage
import com.example.sandbox_compose.ui.theme.BlackGreen
import com.example.sandbox_compose.ui.theme.Purple40
import com.example.sandbox_compose.ui.theme.Sandbox_composeTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val items = listOf(
                   NavDestination.Home, NavDestination.Order, NavDestination.Profile
            )
            var selectedIndex by remember { mutableIntStateOf(0) }

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            Sandbox_composeTheme {
                Scaffold(
                       modifier = Modifier
                           .fillMaxSize(),
                       // .padding(WindowInsets.safeDrawing.asPaddingValues()),
                       //containerColor = Purple40,
                       bottomBar = {
                           if(currentRoute != "login_page"){
                               NavigationBar(
                                      containerColor = Color.White
                               ) {
                                   items.forEachIndexed { index, screen ->
                                       NavigationBarItem(
                                              icon = {
                                                  Icon(
                                                         painter = painterResource(id = screen.iconId),
                                                         contentDescription = null
                                                  )
                                              },
                                              label = { Text(screen.title) },
                                              selected = currentRoute == screen.route, // Используем currentRoute для выделения,
                                              onClick = {
                                                  selectedIndex = index
                                                  navController.navigate(screen.route) {
                                                      // Pop up to the start destination of the graph to
                                                      // avoid building up a large stack of destinations
                                                      // on the back stack as users select items
                                                      popUpTo(navController.graph.findStartDestination().id) {
                                                          saveState = true
                                                      }
                                                      // Avoid multiple copies of the same destination when
                                                      // reselecting the same item
                                                      launchSingleTop = true
                                                      // Restore state when reselecting a previously selected item
                                                      restoreState = true
                                                  }
                                              },
                                              colors = NavigationBarItemDefaults.colors(
                                                     selectedIconColor = BlackGreen,
                                                     selectedTextColor = BlackGreen,
                                                     indicatorColor = Color.Transparent
                                              )
                                       )
                                   }
                               }
                           }

                       }
                ) { innerPadding ->
                    NavigationHost(
                           navController = navController,
                           // ktorClient = ktorClient,
                           innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationHost(
       navController: NavHostController,
       // ktorClient: KtorClient,
       innerPadding: PaddingValues
) {
    NavHost(
           navController = navController,
           startDestination = NavDestination.Home.route,
           modifier = androidx.compose.ui.Modifier
               //.background(color = Purple40)
               .padding(innerPadding)
    ) {

        composable(route = "login_page") {
            LoginPage(navController)
        }

        composable(route = NavDestination.Home.route) {
            HomePage(navController = navController)
        }
        composable<ProductDetails>(
               typeMap = mapOf(typeOf<UiProductModel>() to productNavType)
        ) {
            val productRoute = it.toRoute<ProductDetails>()
            ProductDetailsPage(
                   navController = navController,
                   product = productRoute.product,
                   onBackClicked = { navController.navigateUp() })
        }
        composable(route = NavDestination.Order.route) {
            OrdersPage()
        }

        composable(route = "cart_page") {
            CartPage(navController)
        }
        composable(
               route = "cart_page/cartSummaryPage"
        ) {
            CartSummaryPage(navController = navController)
        }
        composable<UserAddressRoute>(
               // route = "${NavDestination.Cart.route}/userAddressPage",
               typeMap = mapOf(typeOf<UserAddressRouteWrapper>() to userAddressNavType)
        ) {
            val userAddressRoute = it.toRoute<UserAddressRoute>()
            UserAddressPage(
                   navController = navController,
                   userAddress = userAddressRoute.userAddressWrapper.userAddress
            )
        }
        composable(
               route = NavDestination.Profile.route,
        ) {
            //profile page
        }
    }
}

sealed class NavDestination(
       val title: String,
       val route: String,
       val iconId: Int // Храним ID ресурса
) {
    object Home : NavDestination(title = "Home", route = "home_page", iconId = R.drawable.baseline_home_filled_24)
    object Order : NavDestination(title = "Order", route = "order_page", iconId = R.drawable.baseline_shopping_basket_24)
    object Profile : NavDestination(title = "Profile", route = "profile_page", iconId = R.drawable.ic_person_24)
}
//sealed class NavDestination(
//       val title: String,
//       val route: String,
//       val icon: ImageVector
//) {
//
//    object Home : NavDestination(title = "Home", route = "home_screen", icon = Icons.Filled.Home)
//    object Order :
//           NavDestination(title = "Order", route = "episodes", icon = Icons.Filled.ShoppingBasket)
//
//    object Profile : NavDestination(title = "Profile", route = "search", icon = Icons.Filled.Person)
//}
