package com.householdmanager.app.data.repository

import com.householdmanager.app.data.dao.HouseDao
import com.householdmanager.app.data.dao.HouseUserDao
import com.householdmanager.app.data.entity.House
import com.householdmanager.app.data.entity.HouseType
import com.householdmanager.app.data.entity.HouseUser
import com.householdmanager.app.data.entity.HouseRole
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HouseRepository @Inject constructor(
    private val houseDao: HouseDao,
    private val houseUserDao: HouseUserDao
) {
    fun getAllHouses(): Flow<List<House>> = houseDao.getAllHouses()
    
    suspend fun getHouseById(houseId: String): House? = houseDao.getHouseById(houseId)
    
    suspend fun insertHouse(house: House) = houseDao.insertHouse(house)
    
    suspend fun updateHouse(house: House) = houseDao.updateHouse(house)
    
    suspend fun deleteHouse(house: House) = houseDao.deleteHouse(house)
    
    fun getUsersByHouse(houseId: String): Flow<List<HouseUser>> = houseUserDao.getUsersByHouse(houseId)
    
    fun getHousesByUser(userId: String): Flow<List<HouseUser>> = houseUserDao.getHousesByUser(userId)
    
    suspend fun addUserToHouse(houseId: String, userId: String, role: HouseRole = HouseRole.MEMBER) {
        val houseUser = HouseUser(
            houseId = houseId,
            userId = userId,
            role = role,
            joinedAt = Date()
        )
        houseUserDao.insertHouseUser(houseUser)
    }
    
    suspend fun removeUserFromHouse(houseId: String, userId: String) {
        houseUserDao.removeUserFromHouse(houseId, userId)
    }
    
    suspend fun createHouse(
        name: String,
        type: HouseType,
        address: String? = null,
        createdByUserId: String
    ): House {
        val house = House(
            id = generateHouseId(),
            name = name,
            type = type,
            address = address,
            createdAt = Date(),
            updatedAt = Date()
        )
        insertHouse(house)
        
        // Add creator as admin
        addUserToHouse(house.id, createdByUserId, HouseRole.ADMIN)
        
        return house
    }
    
    private fun generateHouseId(): String {
        return "house_${System.currentTimeMillis()}_${(1000..9999).random()}"
    }
}