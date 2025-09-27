package com.householdmanager.app.ui.screens.groceries

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.householdmanager.app.ui.components.GroceryItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceriesScreen(
    onNavigateBack: () -> Unit
) {
    var showAddItemDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Groceries & Fridge", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddItemDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(getSampleGroceryItems()) { item ->
                GroceryItemCard(
                    item = item,
                    onConsume = { /* TODO: Implement consume */ },
                    onEdit = { /* TODO: Implement edit */ },
                    onDelete = { /* TODO: Implement delete */ }
                )
            }
        }
    }
    
    if (showAddItemDialog) {
        AddGroceryItemDialog(
            onDismiss = { showAddItemDialog = false },
            onSave = { /* TODO: Implement save */ }
        )
    }
}

@Composable
fun AddGroceryItemDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, String, String) -> Unit
) {
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Fruits") }
    var expiryDate by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Grocery Item") },
        text = {
            Column {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text("Item Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantity") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = expiryDate,
                    onValueChange = { expiryDate = it },
                    label = { Text("Expiry Date") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSave(itemName, quantity, category, expiryDate)
                    onDismiss()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

private fun getSampleGroceryItems() = listOf(
    SampleGroceryItem("Milk", "1L", "Dairy", "2024-01-20", "Fridge"),
    SampleGroceryItem("Bananas", "6 pieces", "Fruits", "2024-01-18", "Counter"),
    SampleGroceryItem("Chicken", "500g", "Meat", "2024-01-22", "Fridge"),
    SampleGroceryItem("Bread", "1 loaf", "Grains", "2024-01-25", "Pantry")
)

data class SampleGroceryItem(
    val name: String,
    val quantity: String,
    val category: String,
    val expiryDate: String,
    val location: String
)