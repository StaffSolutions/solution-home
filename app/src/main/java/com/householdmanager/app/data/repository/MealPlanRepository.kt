package com.householdmanager.app.data.repository

import com.householdmanager.app.data.dao.MealPlanDao
import com.householdmanager.app.data.entity.MealPlan
import com.householdmanager.app.data.entity.MealType
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealPlanRepository @Inject constructor(
    private val mealPlanDao: MealPlanDao
) {
    fun getAllMealPlans(): Flow<List<MealPlan>> = mealPlanDao.getAllMealPlans()
    
    suspend fun getMealPlanById(mealPlanId: String): MealPlan? = mealPlanDao.getMealPlanById(mealPlanId)
    
    fun getMealPlansByDate(date: Date): Flow<List<MealPlan>> = mealPlanDao.getMealPlansByDate(date)
    
    fun getMealPlansByDateRange(startDate: Date, endDate: Date): Flow<List<MealPlan>> = 
        mealPlanDao.getMealPlansByDateRange(startDate, endDate)
    
    fun getMealPlansByType(mealType: MealType): Flow<List<MealPlan>> = 
        mealPlanDao.getMealPlansByType(mealType)
    
    suspend fun insertMealPlan(mealPlan: MealPlan) = mealPlanDao.insertMealPlan(mealPlan)
    
    suspend fun updateMealPlan(mealPlan: MealPlan) = mealPlanDao.updateMealPlan(mealPlan)
    
    suspend fun deleteMealPlan(mealPlan: MealPlan) = mealPlanDao.deleteMealPlan(mealPlan)
    
    suspend fun createMealPlan(
        date: Date,
        mealType: MealType,
        name: String,
        description: String? = null,
        ingredients: List<String> = emptyList(),
        createdBy: String
    ): MealPlan {
        val mealPlan = MealPlan(
            id = generateMealPlanId(),
            date = date,
            mealType = mealType,
            name = name,
            description = description,
            ingredients = ingredients,
            createdBy = createdBy,
            createdAt = Date(),
            updatedAt = Date()
        )
        insertMealPlan(mealPlan)
        return mealPlan
    }
    
    private fun generateMealPlanId(): String {
        return "meal_${System.currentTimeMillis()}_${(1000..9999).random()}"
    }
}