package com.householdmanager.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "houses")
data class House(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: HouseType,
    val address: String? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

enum class HouseType {
    APARTMENT,
    HOUSE,
    CONDO,
    TOWNHOUSE
}