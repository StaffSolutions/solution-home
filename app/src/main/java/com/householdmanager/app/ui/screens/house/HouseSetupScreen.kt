package com.householdmanager.app.ui.screens.house

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.householdmanager.app.data.entity.HouseType
import com.householdmanager.app.ui.components.HouseTypeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseSetupScreen(
    onNavigateToDashboard: () -> Unit
) {
    var houseName by remember { mutableStateOf("") }
    var selectedHouseType by remember { mutableStateOf<HouseType?>(null) }
    var isNameError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Set Up Your House",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = houseName,
            onValueChange = { 
                houseName = it
                isNameError = false
            },
            label = { Text("House Name") },
            placeholder = { Text("Enter house name") },
            isError = isNameError,
            supportingText = if (isNameError) {
                { Text("Please enter a house name") }
            } else null,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "What type of home do you live in?",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Column(
            modifier = Modifier.selectableGroup()
        ) {
            HouseType.values().forEach { houseType ->
                HouseTypeCard(
                    houseType = houseType,
                    isSelected = selectedHouseType == houseType,
                    onClick = { selectedHouseType = houseType },
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedHouseType == houseType,
                            onClick = { selectedHouseType = houseType },
                            role = Role.RadioButton
                        )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = {
                if (houseName.isBlank()) {
                    isNameError = true
                } else if (selectedHouseType != null) {
                    // TODO: Save house setup to database
                    onNavigateToDashboard()
                }
            },
            enabled = houseName.isNotBlank() && selectedHouseType != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}