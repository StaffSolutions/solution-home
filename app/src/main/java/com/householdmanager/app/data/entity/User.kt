package com.householdmanager.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val isAdmin: Boolean = false,
    val isActive: Boolean = true,
    val createdAt: Date = Date(),
    val lastLoginAt: Date? = null
)