package com.householdmanager.app.ui.navigation

sealed class Screen(val route: String) {
    object HouseSetup : Screen("house_setup")
    object Dashboard : Screen("dashboard")
    object Bills : Screen("bills")
    object Tasks : Screen("tasks")
    object Groceries : Screen("groceries")
    object Settings : Screen("settings")
}