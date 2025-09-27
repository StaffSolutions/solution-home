package com.householdmanager.app.data.dao

import androidx.room.*
import com.householdmanager.app.data.entity.Task
import com.householdmanager.app.data.entity.TaskStatus
import com.householdmanager.app.data.entity.TaskPriority
import com.householdmanager.app.data.entity.TaskCategory
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAllTasks(): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: String): Task?
    
    @Query("SELECT * FROM tasks WHERE status = :status ORDER BY priority DESC, dueDate ASC")
    fun getTasksByStatus(status: TaskStatus): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE assignedTo = :userId ORDER BY dueDate ASC")
    fun getTasksByAssignee(userId: String): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE createdBy = :userId ORDER BY createdAt DESC")
    fun getTasksByCreator(userId: String): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE priority = :priority ORDER BY dueDate ASC")
    fun getTasksByPriority(priority: TaskPriority): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE category = :category ORDER BY dueDate ASC")
    fun getTasksByCategory(category: TaskCategory): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE dueDate BETWEEN :startDate AND :endDate")
    fun getTasksByDateRange(startDate: Date, endDate: Date): Flow<List<Task>>
    
    @Query("SELECT * FROM tasks WHERE status != 'COMPLETED' AND dueDate < :currentDate")
    fun getOverdueTasks(currentDate: Date): Flow<List<Task>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)
    
    @Update
    suspend fun updateTask(task: Task)
    
    @Delete
    suspend fun deleteTask(task: Task)
    
    @Query("UPDATE tasks SET status = :status, completedAt = :completedAt WHERE id = :taskId")
    suspend fun updateTaskStatus(taskId: String, status: TaskStatus, completedAt: Date?)
}