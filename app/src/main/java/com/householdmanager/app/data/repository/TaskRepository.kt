package com.householdmanager.app.data.repository

import com.householdmanager.app.data.dao.TaskDao
import com.householdmanager.app.data.entity.Task
import com.householdmanager.app.data.entity.TaskStatus
import com.householdmanager.app.data.entity.TaskPriority
import com.householdmanager.app.data.entity.TaskCategory
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
    
    suspend fun getTaskById(taskId: String): Task? = taskDao.getTaskById(taskId)
    
    fun getTasksByStatus(status: TaskStatus): Flow<List<Task>> = taskDao.getTasksByStatus(status)
    
    fun getTasksByAssignee(userId: String): Flow<List<Task>> = taskDao.getTasksByAssignee(userId)
    
    fun getTasksByCreator(userId: String): Flow<List<Task>> = taskDao.getTasksByCreator(userId)
    
    fun getTasksByPriority(priority: TaskPriority): Flow<List<Task>> = taskDao.getTasksByPriority(priority)
    
    fun getTasksByCategory(category: TaskCategory): Flow<List<Task>> = taskDao.getTasksByCategory(category)
    
    fun getTasksByDateRange(startDate: Date, endDate: Date): Flow<List<Task>> = 
        taskDao.getTasksByDateRange(startDate, endDate)
    
    fun getOverdueTasks(currentDate: Date): Flow<List<Task>> = taskDao.getOverdueTasks(currentDate)
    
    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    
    suspend fun updateTaskStatus(taskId: String, status: TaskStatus, completedAt: Date? = null) = 
        taskDao.updateTaskStatus(taskId, status, completedAt)
    
    suspend fun createTask(
        title: String,
        description: String? = null,
        priority: TaskPriority,
        assignedTo: String? = null,
        createdBy: String,
        dueDate: Date? = null,
        category: TaskCategory? = null
    ): Task {
        val task = Task(
            id = generateTaskId(),
            title = title,
            description = description,
            priority = priority,
            status = TaskStatus.TODO,
            assignedTo = assignedTo,
            createdBy = createdBy,
            dueDate = dueDate,
            category = category,
            createdAt = Date(),
            updatedAt = Date()
        )
        insertTask(task)
        return task
    }
    
    private fun generateTaskId(): String {
        return "task_${System.currentTimeMillis()}_${(1000..9999).random()}"
    }
}