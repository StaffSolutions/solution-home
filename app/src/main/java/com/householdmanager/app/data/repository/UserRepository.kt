package com.householdmanager.app.data.repository

import com.householdmanager.app.data.dao.UserDao
import com.householdmanager.app.data.entity.User
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getAllActiveUsers(): Flow<List<User>> = userDao.getAllActiveUsers()
    
    suspend fun getUserById(userId: String): User? = userDao.getUserById(userId)
    
    suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)
    
    fun getAdmins(): Flow<List<User>> = userDao.getAdmins()
    
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    
    suspend fun deactivateUser(userId: String) = userDao.deactivateUser(userId)
    
    suspend fun createUser(
        name: String,
        email: String,
        isAdmin: Boolean = false
    ): User {
        val user = User(
            id = generateUserId(),
            name = name,
            email = email,
            isAdmin = isAdmin,
            isActive = true,
            createdAt = Date()
        )
        insertUser(user)
        return user
    }
    
    private fun generateUserId(): String {
        return "user_${System.currentTimeMillis()}_${(1000..9999).random()}"
    }
}