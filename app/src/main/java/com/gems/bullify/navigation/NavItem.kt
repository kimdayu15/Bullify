package com.gems.bullify.navigation

sealed class NavItem(val route: String) {
    object WelcomePageScreen : NavItem("welcome_page")
    object ProfileSetPage : NavItem("profile_set")

}