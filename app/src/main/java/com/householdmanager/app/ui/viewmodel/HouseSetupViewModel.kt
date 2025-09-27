package com.householdmanager.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.householdmanager.app.data.entity.HouseType
import com.householdmanager.app.data.repository.HouseRepository
import com.householdmanager.app.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseSetupViewModel @Inject constructor(
    private val houseRepository: HouseRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HouseSetupUiState())
    val uiState: StateFlow<HouseSetupUiState> = _uiState.asStateFlow()
    
    fun updateHouseName(name: String) {
        _uiState.value = _uiState.value.copy(
            houseName = name,
            isNameError = false
        )
    }
    
    fun selectHouseType(type: HouseType) {
        _uiState.value = _uiState.value.copy(selectedHouseType = type)
    }
    
    fun updateAddress(address: String) {
        _uiState.value = _uiState.value.copy(address = address)
    }
    
    fun createHouse() {
        val currentState = _uiState.value
        
        if (currentState.houseName.isBlank()) {
            _uiState.value = currentState.copy(isNameError = true)
            return
        }
        
        if (currentState.selectedHouseType == null) {
            return
        }
        
        viewModelScope.launch {
            try {
                _uiState.value = currentState.copy(isLoading = true)
                
                // Create a default admin user for the house
                val adminUser = userRepository.createUser(
                    name = "House Admin",
                    email = "admin@household.com",
                    isAdmin = true
                )
                
                // Create the house
                val house = houseRepository.createHouse(
                    name = currentState.houseName,
                    type = currentState.selectedHouseType!!,
                    address = currentState.address.takeIf { it.isNotBlank() },
                    createdByUserId = adminUser.id
                )
                
                _uiState.value = currentState.copy(
                    isLoading = false,
                    isSuccess = true,
                    createdHouseId = house.id
                )
            } catch (e: Exception) {
                _uiState.value = currentState.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to create house"
                )
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class HouseSetupUiState(
    val houseName: String = "",
    val selectedHouseType: HouseType? = null,
    val address: String = "",
    val isNameError: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val createdHouseId: String? = null,
    val error: String? = null
)