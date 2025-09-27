package com.householdmanager.app.data.dao

import androidx.room.*
import com.householdmanager.app.data.entity.House
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseDao {
    @Query("SELECT * FROM houses")
    fun getAllHouses(): Flow<List<House>>
    
    @Query("SELECT * FROM houses WHERE id = :houseId")
    suspend fun getHouseById(houseId: String): House?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHouse(house: House)
    
    @Update
    suspend fun updateHouse(house: House)
    
    @Delete
    suspend fun deleteHouse(house: House)
}