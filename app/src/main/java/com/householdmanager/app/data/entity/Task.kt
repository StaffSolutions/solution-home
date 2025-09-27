package com.householdmanager.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String? = null,
    val priority: TaskPriority,
    val status: TaskStatus = TaskStatus.TODO,
    val assignedTo: String? = null, // User ID
    val createdBy: String, // User ID
    val dueDate: Date? = null,
    val completedAt: Date? = null,
    val category: TaskCategory? = null,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH
}

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}

enum class TaskCategory {
    RENOVATION,
    MAINTENANCE,
    SHOPPING,
    CLEANING,
    ORGANIZATION,
    OTHER
}