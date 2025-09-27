package com.householdmanager.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.householdmanager.app.ui.screens.house.HouseSetupScreen
import com.householdmanager.app.ui.screens.dashboard.DashboardScreen
import com.householdmanager.app.ui.screens.bills.BillsScreen
import com.householdmanager.app.ui.screens.tasks.TasksScreen
import com.householdmanager.app.ui.screens.groceries.GroceriesScreen
import com.householdmanager.app.ui.screens.settings.SettingsScreen

@Composable
fun HouseholdNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HouseSetup.route
    ) {
        composable(Screen.HouseSetup.route) {
            HouseSetupScreen(
                onNavigateToDashboard = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.HouseSetup.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToBills = { navController.navigate(Screen.Bills.route) },
                onNavigateToTasks = { navController.navigate(Screen.Tasks.route) },
                onNavigateToGroceries = { navController.navigate(Screen.Groceries.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        
        composable(Screen.Bills.route) {
            BillsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Tasks.route) {
            TasksScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Groceries.route) {
            GroceriesScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}