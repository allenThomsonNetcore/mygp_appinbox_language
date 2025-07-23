package com.mygp.composeactivity.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mygp.composeactivity.screens.CartScreen
import com.mygp.composeactivity.screens.LoginScreen
import com.mygp.composeactivity.screens.ProfileScreen
import com.mygp.composeactivity.HomeActivity
import com.mygp.composeactivity.screens.HomeScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Profile : Screen("profile")
    object Cart : Screen("cart")
    object Home : Screen("home")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    onLoginSuccess: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    onLoginSuccess()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        composable(Screen.Cart.route) {
            CartScreen()
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onCartClick = { navController.navigate(Screen.Cart.route) }
            )
        }
    }
}
