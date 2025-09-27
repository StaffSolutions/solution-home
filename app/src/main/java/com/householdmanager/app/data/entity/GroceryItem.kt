package com.householdmanager.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.Date

@Entity(tableName = "grocery_items")
data class GroceryItem(
    @PrimaryKey
    val id: String,
    val name: String,
    val quantity: BigDecimal,
    val unit: String, // kg, lbs, pieces, etc.
    val category: GroceryCategory,
    val expiryDate: Date? = null,
    val purchaseDate: Date = Date(),
    val isConsumed: Boolean = false,
    val consumedAt: Date? = null,
    val location: StorageLocation = StorageLocation.FRIDGE,
    val addedBy: String, // User ID
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

enum class GroceryCategory {
    FRUITS,
    VEGETABLES,
    DAIRY,
    MEAT,
    SEAFOOD,
    GRAINS,
    BEVERAGES,
    SNACKS,
    CONDIMENTS,
    FROZEN,
    OTHER
}

enum class StorageLocation {
    FRIDGE,
    FREEZER,
    PANTRY,
    COUNTER,
    CABINET
}