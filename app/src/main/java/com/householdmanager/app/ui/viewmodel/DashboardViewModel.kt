package com.householdmanager.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.householdmanager.app.data.repository.BillRepository
import com.householdmanager.app.data.repository.TaskRepository
import com.householdmanager.app.data.repository.GroceryItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val billRepository: BillRepository,
    private val taskRepository: TaskRepository,
    private val groceryItemRepository: GroceryItemRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()
    
    init {
        loadDashboardData()
    }
    
    private fun loadDashboardData() {
        viewModelScope.launch {
            try {
                combine(
                    billRepository.getUnpaidBills(),
                    taskRepository.getTasksByStatus(com.householdmanager.app.data.entity.TaskStatus.TODO),
                    groceryItemRepository.getAllActiveItems()
                ) { bills, tasks, groceries ->
                    DashboardUiState(
                        upcomingBillsCount = bills.size,
                        upcomingBillsAmount = bills.sumOf { it.amount },
                        activeTasksCount = tasks.size,
                        overdueTasksCount = tasks.count { 
                            it.dueDate?.before(java.util.Date()) == true 
                        },
                        groceryItemsCount = groceries.size,
                        expiringItemsCount = groceries.count { 
                            it.expiryDate?.let { expiry ->
                                val threeDaysFromNow = java.util.Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000)
                                expiry.before(threeDaysFromNow)
                            } ?: false
                        },
                        isLoading = false
                    )
                }.collect { newState ->
                    _uiState.value = newState
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load dashboard data"
                )
            }
        }
    }
    
    fun refresh() {
        loadDashboardData()
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class DashboardUiState(
    val upcomingBillsCount: Int = 0,
    val upcomingBillsAmount: java.math.BigDecimal = java.math.BigDecimal.ZERO,
    val activeTasksCount: Int = 0,
    val overdueTasksCount: Int = 0,
    val groceryItemsCount: Int = 0,
    val expiringItemsCount: Int = 0,
    val isLoading: Boolean = true,
    val error: String? = null
)