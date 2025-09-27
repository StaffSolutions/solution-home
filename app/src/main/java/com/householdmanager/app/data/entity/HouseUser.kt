package com.householdmanager.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "house_users",
    primaryKeys = ["houseId", "userId"]
)
data class HouseUser(
    val houseId: String,
    val userId: String,
    val role: HouseRole = HouseRole.MEMBER,
    val joinedAt: Date = Date()
)

enum class HouseRole {
    ADMIN,
    MEMBER
}