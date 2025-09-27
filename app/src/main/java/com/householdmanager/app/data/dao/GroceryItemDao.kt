package com.householdmanager.app.data.dao

import androidx.room.*
import com.householdmanager.app.data.entity.GroceryItem
import com.householdmanager.app.data.entity.GroceryCategory
import com.householdmanager.app.data.entity.StorageLocation
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface GroceryItemDao {
    @Query("SELECT * FROM grocery_items WHERE isConsumed = 0 ORDER BY expiryDate ASC")
    fun getAllActiveItems(): Flow<List<GroceryItem>>
    
    @Query("SELECT * FROM grocery_items WHERE id = :itemId")
    suspend fun getItemById(itemId: String): GroceryItem?
    
    @Query("SELECT * FROM grocery_items WHERE category = :category AND isConsumed = 0 ORDER BY expiryDate ASC")
    fun getItemsByCategory(category: GroceryCategory): Flow<List<GroceryItem>>
    
    @Query("SELECT * FROM grocery_items WHERE location = :location AND isConsumed = 0 ORDER BY expiryDate ASC")
    fun getItemsByLocation(location: StorageLocation): Flow<List<GroceryItem>>
    
    @Query("SELECT * FROM grocery_items WHERE expiryDate BETWEEN :startDate AND :endDate AND isConsumed = 0")
    fun getItemsExpiringBetween(startDate: Date, endDate: Date): Flow<List<GroceryItem>>
    
    @Query("SELECT * FROM grocery_items WHERE expiryDate < :currentDate AND isConsumed = 0")
    fun getExpiredItems(currentDate: Date): Flow<List<GroceryItem>>
    
    @Query("SELECT * FROM grocery_items WHERE expiryDate BETWEEN :currentDate AND :warningDate AND isConsumed = 0")
    fun getItemsExpiringSoon(currentDate: Date, warningDate: Date): Flow<List<GroceryItem>>
    
    @Query("SELECT * FROM grocery_items WHERE isConsumed = 1 ORDER BY consumedAt DESC")
    fun getConsumedItems(): Flow<List<GroceryItem>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: GroceryItem)
    
    @Update
    suspend fun updateItem(item: GroceryItem)
    
    @Delete
    suspend fun deleteItem(item: GroceryItem)
    
    @Query("UPDATE grocery_items SET isConsumed = 1, consumedAt = :consumedAt WHERE id = :itemId")
    suspend fun markItemAsConsumed(itemId: String, consumedAt: Date)
}