package com.householdmanager.app.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.householdmanager.app.ui.components.DashboardCard
import com.householdmanager.app.ui.components.QuickActionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToBills: () -> Unit,
    onNavigateToTasks: () -> Unit,
    onNavigateToGroceries: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Household Manager",
                        fontWeight = FontWeight.Bold
                    ) 
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Welcome Home!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(getQuickActions()) { action ->
                        QuickActionCard(
                            title = action.title,
                            icon = action.icon,
                            onClick = action.onClick
                        )
                    }
                }
            }
            
            item {
                Text(
                    text = "Overview",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            items(getDashboardCards()) { card ->
                DashboardCard(
                    title = card.title,
                    subtitle = card.subtitle,
                    value = card.value,
                    icon = card.icon,
                    onClick = card.onClick
                )
            }
        }
    }
}

private fun getQuickActions() = listOf(
    QuickAction("Add Bill", Icons.Default.Receipt) { },
    QuickAction("New Task", Icons.Default.AddTask) { },
    QuickAction("Add Grocery", Icons.Default.ShoppingCart) { },
    QuickAction("Meal Plan", Icons.Default.Restaurant) { }
)

private fun getDashboardCards() = listOf(
    DashboardCardData(
        title = "Upcoming Bills",
        subtitle = "3 bills due this week",
        value = "$245.50",
        icon = Icons.Default.AccountBalance,
        onClick = { }
    ),
    DashboardCardData(
        title = "Active Tasks",
        subtitle = "5 tasks in progress",
        value = "2 overdue",
        icon = Icons.Default.Assignment,
        onClick = { }
    ),
    DashboardCardData(
        title = "Groceries",
        subtitle = "12 items in fridge",
        value = "3 expiring soon",
        icon = Icons.Default.Kitchen,
        onClick = { }
    ),
    DashboardCardData(
        title = "Meal Plans",
        subtitle = "This week",
        value = "7 planned",
        icon = Icons.Default.Restaurant,
        onClick = { }
    )
)

data class QuickAction(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val onClick: () -> Unit
)

data class DashboardCardData(
    val title: String,
    val subtitle: String,
    val value: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val onClick: () -> Unit
)