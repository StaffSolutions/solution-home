package com.householdmanager.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.Date

@Entity(tableName = "bills")
data class Bill(
    @PrimaryKey
    val id: String,
    val name: String,
    val amount: BigDecimal,
    val type: BillType,
    val dueDate: Date,
    val isPaid: Boolean = false,
    val paidDate: Date? = null,
    val reminderDays: Int = 3, // Days before due date to remind
    val isRecurring: Boolean = false,
    val recurringInterval: RecurringInterval? = null,
    val createdBy: String, // User ID
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

enum class BillType {
    FIXED_RATE,
    MANUAL_ENTRY
}

enum class RecurringInterval {
    MONTHLY,
    QUARTERLY,
    YEARLY
}