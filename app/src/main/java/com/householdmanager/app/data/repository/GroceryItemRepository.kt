package com.householdmanager.app.data.repository

import com.householdmanager.app.data.dao.GroceryItemDao
import com.householdmanager.app.data.entity.GroceryItem
import com.householdmanager.app.data.entity.GroceryCategory
import com.householdmanager.app.data.entity.StorageLocation
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroceryItemRepository @Inject constructor(
    private val groceryItemDao: GroceryItemDao
) {
    fun getAllActiveItems(): Flow<List<GroceryItem>> = groceryItemDao.getAllActiveItems()
    
    suspend fun getItemById(itemId: String): GroceryItem? = groceryItemDao.getItemById(itemId)
    
    fun getItemsByCategory(category: GroceryCategory): Flow<List<GroceryItem>> = 
        groceryItemDao.getItemsByCategory(category)
    
    fun getItemsByLocation(location: StorageLocation): Flow<List<GroceryItem>> = 
        groceryItemDao.getItemsByLocation(location)
    
    fun getItemsExpiringBetween(startDate: Date, endDate: Date): Flow<List<GroceryItem>> = 
        groceryItemDao.getItemsExpiringBetween(startDate, endDate)
    
    fun getExpiredItems(currentDate: Date): Flow<List<GroceryItem>> = 
        groceryItemDao.getExpiredItems(currentDate)
    
    fun getItemsExpiringSoon(currentDate: Date, warningDate: Date): Flow<List<GroceryItem>> = 
        groceryItemDao.getItemsExpiringSoon(currentDate, warningDate)
    
    fun getConsumedItems(): Flow<List<GroceryItem>> = groceryItemDao.getConsumedItems()
    
    suspend fun insertItem(item: GroceryItem) = groceryItemDao.insertItem(item)
    
    suspend fun updateItem(item: GroceryItem) = groceryItemDao.updateItem(item)
    
    suspend fun deleteItem(item: GroceryItem) = groceryItemDao.deleteItem(item)
    
    suspend fun markItemAsConsumed(itemId: String, consumedAt: Date = Date()) = 
        groceryItemDao.markItemAsConsumed(itemId, consumedAt)
    
    suspend fun createGroceryItem(
        name: String,
        quantity: java.math.BigDecimal,
        unit: String,
        category: GroceryCategory,
        expiryDate: Date? = null,
        location: StorageLocation = StorageLocation.FRIDGE,
        addedBy: String
    ): GroceryItem {
        val item = GroceryItem(
            id = generateItemId(),
            name = name,
            quantity = quantity,
            unit = unit,
            category = category,
            expiryDate = expiryDate,
            purchaseDate = Date(),
            isConsumed = false,
            location = location,
            addedBy = addedBy,
            createdAt = Date(),
            updatedAt = Date()
        )
        insertItem(item)
        return item
    }
    
    private fun generateItemId(): String {
        return "grocery_${System.currentTimeMillis()}_${(1000..9999).random()}"
    }
}