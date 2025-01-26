package com.gems.bullify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gems.bullify.ui.screens.ProfileSetPage
import com.gems.bullify.ui.screens.WelcomePage

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavItem.WelcomePageScreen.route
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavItem.WelcomePageScreen.route) {
            WelcomePage(navController)
        }

        composable(NavItem.ProfileSetPage.route) {
            ProfileSetPage()
        }
    }
}