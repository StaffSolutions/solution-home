package com.householdmanager.app.ui.screens.bills

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
import com.householdmanager.app.ui.components.BillCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillsScreen(
    onNavigateBack: () -> Unit
) {
    var showAddBillDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bills", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddBillDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Bill")
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
            items(getSampleBills()) { bill ->
                BillCard(
                    bill = bill,
                    onMarkAsPaid = { /* TODO: Implement mark as paid */ },
                    onEdit = { /* TODO: Implement edit */ },
                    onDelete = { /* TODO: Implement delete */ }
                )
            }
        }
    }
    
    if (showAddBillDialog) {
        AddBillDialog(
            onDismiss = { showAddBillDialog = false },
            onSave = { /* TODO: Implement save */ }
        )
    }
}

@Composable
fun AddBillDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, String) -> Unit
) {
    var billName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Bill") },
        text = {
            Column {
                OutlinedTextField(
                    value = billName,
                    onValueChange = { billName = it },
                    label = { Text("Bill Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                OutlinedTextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due Date") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSave(billName, amount, dueDate)
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

private fun getSampleBills() = listOf(
    SampleBill("Electricity", "$120.50", "2024-01-15", false),
    SampleBill("Water", "$45.00", "2024-01-20", false),
    SampleBill("Internet", "$79.99", "2024-01-25", false),
    SampleBill("Rent", "$1,200.00", "2024-01-01", true)
)

data class SampleBill(
    val name: String,
    val amount: String,
    val dueDate: String,
    val isPaid: Boolean
)