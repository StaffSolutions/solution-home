package com.householdmanager.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "meal_plans")
data class MealPlan(
    @PrimaryKey
    val id: String,
    val date: Date,
    val mealType: MealType,
    val name: String,
    val description: String? = null,
    val ingredients: List<String> = emptyList(), // List of grocery item IDs
    val createdBy: String, // User ID
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

enum class MealType {
    BREAKFAST,
    LUNCH,
    DINNER,
    SNACK
}