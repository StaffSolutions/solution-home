package com.householdmanager.app.data.dao

import androidx.room.*
import com.householdmanager.app.data.entity.HouseUser
import com.householdmanager.app.data.entity.HouseRole
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseUserDao {
    @Query("SELECT * FROM house_users WHERE houseId = :houseId")
    fun getUsersByHouse(houseId: String): Flow<List<HouseUser>>
    
    @Query("SELECT * FROM house_users WHERE userId = :userId")
    fun getHousesByUser(userId: String): Flow<List<HouseUser>>
    
    @Query("SELECT * FROM house_users WHERE houseId = :houseId AND userId = :userId")
    suspend fun getHouseUser(houseId: String, userId: String): HouseUser?
    
    @Query("SELECT * FROM house_users WHERE houseId = :houseId AND role = :role")
    fun getUsersByHouseAndRole(houseId: String, role: HouseRole): Flow<List<HouseUser>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHouseUser(houseUser: HouseUser)
    
    @Update
    suspend fun updateHouseUser(houseUser: HouseUser)
    
    @Delete
    suspend fun deleteHouseUser(houseUser: HouseUser)
    
    @Query("DELETE FROM house_users WHERE houseId = :houseId AND userId = :userId")
    suspend fun removeUserFromHouse(houseId: String, userId: String)
}