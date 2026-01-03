package com.example.mycoursesapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.core.ui.theme.DarkGrey
import com.example.core.ui.theme.Green
import com.example.core.ui.theme.LightGrey
import com.example.core.ui.theme.White
import com.example.features.account.presentation.AccountScreen
import com.example.features.auth.presentation.LoginScreen
import com.example.features.favorites.presentation.FavoritesScreen
import com.example.features.main.presentation.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var isLoggedIn by remember { mutableStateOf(false) }

    if (!isLoggedIn) {
        LoginScreen(onLoginSuccess = { isLoggedIn = true })
    } else {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screens.Main.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screens.Main.route) {
                    MainScreen()
                }
                composable(Screens.Favorites.route) {
                    FavoritesScreen()
                }
                composable(Screens.Account.route) {
                    AccountScreen()
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier,
        containerColor = DarkGrey
    ) {
        NavigationBarItem(
            selected = currentRoute == Screens.Main.route,
            onClick = { navController.navigate(Screens.Main.route) },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Главная") },
            label = { Text("Главная") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = LightGrey,
                selectedIconColor = Green,
                selectedTextColor = Green,
                disabledIconColor = White,
                disabledTextColor = White
            )
        )

        NavigationBarItem(
            selected = currentRoute == Screens.Favorites.route,
            onClick = { navController.navigate(Screens.Favorites.route) },
            icon = { Icon(Icons.Default.BookmarkBorder, contentDescription = "Избранное") },
            label = { Text("Избранное") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = LightGrey,
                selectedIconColor = Green,
                selectedTextColor = Green,
                disabledIconColor = White,
                disabledTextColor = White
            )
        )

        NavigationBarItem(
            selected = currentRoute == Screens.Account.route,
            onClick = { navController.navigate(Screens.Account.route) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Аккаунт") },
            label = { Text("Аккаунт") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = LightGrey,
                selectedIconColor = Green,
                selectedTextColor = Green,
                disabledIconColor = White,
                disabledTextColor = White
            )
        )
    }
}

sealed class Screens(val route: String) {
    object Main : Screens("main")
    object Favorites : Screens("favorites")
    object Account : Screens("account")
}