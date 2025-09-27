package com.householdmanager.app.data.dao

import androidx.room.*
import com.householdmanager.app.data.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE isActive = 1")
    fun getAllActiveUsers(): Flow<List<User>>
    
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): User?
    
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?
    
    @Query("SELECT * FROM users WHERE isAdmin = 1 AND isActive = 1")
    fun getAdmins(): Flow<List<User>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    
    @Update
    suspend fun updateUser(user: User)
    
    @Delete
    suspend fun deleteUser(user: User)
    
    @Query("UPDATE users SET isActive = 0 WHERE id = :userId")
    suspend fun deactivateUser(userId: String)
}